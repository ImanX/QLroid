package com.github.imanx.QLroid.request;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.github.imanx.QLroid.GraphCore;
import com.github.imanx.QLroid.Mutation;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.annonations.SerializedField;
import com.github.imanx.QLroid.annonations.UnInject;
import com.github.imanx.QLroid.callback.Callback;
import com.github.imanx.QLroid.utility.JsonUtility;
import com.zarinpal.libs.httpRequest.HttpRequest;
import com.zarinpal.libs.httpRequest.OnCallbackRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;


/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Request {

    public static final String TAG = "TAG_A";

    private Builder  builder;
    private Callback callback;
    private String   cleanJsonReturn;

    private Request(Builder builder) {
        this.builder = builder;
    }


    public void enqueue(Callback callback) {
        this.callback = callback;
        enqueue();
    }

    public void enqueue() {

        if (this.builder.graphCore == null) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("operationName", null);
                    jsonObject.put("query", builder.getGraphCore().getQuery());
                    jsonObject.put("variable", "{}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                new HttpRequest(builder.getContext())
                        .setRequestMethod(HttpRequest.POST)
                        .setHeaders(builder.getHeader().getMap())
                        .setTimeOut(builder.getTimeout())
                        .setURL(builder.getUri().toString())
                        .setJson(jsonObject)
                        .get(new OnCallbackRequestListener() {
                            @Override
                            public void onSuccessResponse(JSONObject jsonObject, String content) {

                                if (callback == null) {
                                    return;
                                }
                                String json   = JsonUtility.getStrJson(jsonObject, builder.getGraphCore().getOperationName());

                                if (builder.getGraphCore().getModel() == null){
                                    callback.onResponse(json);
                                    return;
                                }

                                String result = iteratorFieldClass(builder.getGraphCore().getModel().getClass(), json);
                                callback.onResponse(result);

                            }

                            @Override
                            public void onFailureResponse(int httpCode, String dataError) {
                                Log.i("TAG", "onFailureResponse: " + httpCode + " || " + dataError);
                                if (callback != null) callback.onFailure();
                            }
                        });
            }

        }).start();

    }

    private String iteratorFieldClass(Class clazz, String json) {

        this.cleanJsonReturn = json;
        iteratorFields(clazz, json);

        Class[] classList = clazz.getDeclaredClasses();

        for (Class myClass : classList) {

            if (myClass.getAnnotation(UnInject.class) != null) {
                continue;
            }
            iteratorFields(myClass, json);
        }
        return this.cleanJsonReturn;
    }

    public void iteratorFields(Class clazz, String json) {

        if (clazz.getAnnotation(SerializedField.class) != null) {
            String temp = ((SerializedField) clazz.getAnnotation(SerializedField.class)).value();
            if (json.contains(temp)) {
                this.cleanJsonReturn = this.cleanJsonReturn.replaceAll(temp, clazz.getSimpleName());
            }
        }

        for (Field field : clazz.getDeclaredFields()) {
            String fieldName;

            if (field.getAnnotation(UnInject.class) != null) {
                continue;
            }

            if (field.getAnnotation(SerializedField.class) != null) {
                String temp = field.getAnnotation(SerializedField.class).value();

                if (json.contains(temp)) {
                    fieldName = field.getName();
                    this.cleanJsonReturn = this.cleanJsonReturn.replaceAll(temp, fieldName);
                }
            }
        }
    }

    // Builder segment

    public static class Builder {

        private Context   context;
        private Uri       uri;
        private GraphCore graphCore;
        private Header    header;
        private int       timeout;

        public Builder(Context context, Uri uri, Query query) {
            this.uri = uri;
            this.graphCore = query;
            this.context = context;
        }

        public Builder(Context context, Uri uri, Mutation mutation) {
            this.uri = uri;
            this.graphCore = mutation;
            this.context = context;
        }

        public Builder(Context context, Uri uri) {
            this.uri = uri;
            this.context = context;
        }

        public Builder setGraph(GraphCore graph) {
            this.graphCore = graph;
            return this;
        }

        public Builder setHeader(Header header) {
            this.header = header;
            return this;
        }

        public Builder setTimeout(int sec) {
            this.timeout = sec;
            return this;
        }

        @Nullable
        public GraphCore getGraphCore() {
            return this.graphCore;
        }

        public Header getHeader() {
            return this.header;
        }

        public Uri getUri() {
            return this.uri;
        }

        public Context getContext() {
            return this.context;
        }

        public Request build() {
            return new Request(this);
        }


        public int getTimeout() {
            return this.timeout;
        }
    }
}
