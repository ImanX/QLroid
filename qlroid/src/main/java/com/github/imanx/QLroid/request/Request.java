package com.github.imanx.QLroid.request;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.JsonReader;
import android.util.Log;

import com.github.imanx.QLroid.GraphCore;
import com.github.imanx.QLroid.GraphModel;
import com.github.imanx.QLroid.utility.JsonUtility;
import com.github.imanx.QLroid.Mutation;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.annonations.SerializeName;
import com.github.imanx.QLroid.annonations.UnInject;
import com.github.imanx.QLroid.callback.Callback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zarinpal.libs.httpRequest.HttpRequest;
import com.zarinpal.libs.httpRequest.OnCallbackRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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

    public String iteratorFieldClass(Class clazz, String json) {
        this.cleanJsonReturn = json;
        for (Field field : clazz.getDeclaredFields()) {
            String fieldName;

            if (field.getAnnotation(UnInject.class) != null) {
                continue;
            }

            if (field.getAnnotation(SerializeName.class) != null) {
                String temp = field.getAnnotation(SerializeName.class).value();

                if (json.contains(temp)) {
                    fieldName = field.getName();
                    this.cleanJsonReturn = this.cleanJsonReturn.replaceAll(temp, fieldName);
                }
            }
        }
        Class[] classList = clazz.getDeclaredClasses();
        for (Class myClass : classList) {
            iteratorFieldClass(myClass, this.cleanJsonReturn);
        }
        return this.cleanJsonReturn;
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
