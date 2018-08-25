package com.github.imanx.QLroid.request;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.github.imanx.QLroid.GraphCore;
import com.github.imanx.QLroid.GraphModel;
import com.github.imanx.QLroid.Mutation;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.callback.Callback;
import com.github.imanx.QLroid.utility.Utility;
import com.zarinpal.libs.httpRequest.HttpRequest;
import com.zarinpal.libs.httpRequest.OnCallbackRequestListener;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Request {

    public static final String TAG = Request.class.getSimpleName();

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


                Log.i(TAG, "run: " + builder.getGraphCore().getQuery());


                new HttpRequest(builder.getContext())
                        .setRequestMethod(HttpRequest.POST)
                        .setHeaders(builder.getHeader().getMap())
                        .setTimeOut(builder.getTimeout())
                        .setURL(builder.getUri().toString())
                        .setJson(jsonObject)
                        .get(new OnCallbackRequestListener() {
                            @Override
                            public void onSuccessResponse(JSONObject jsonObject, String content) {

                                GraphModel model       = builder.getGraphCore().getModel();
                                String     masterKey   = (model == null) ? builder.getGraphCore().getOperationName() : model.getResponseModelName();
                                String     wrappedJson = Utility.getWrappedJson(jsonObject, masterKey);


                                if (callback == null) {
                                    return;
                                }

                                if (wrappedJson == null) {
                                    callback.onResponse(content);
                                    return;
                                }


                                if (model == null) {
                                    callback.onResponse(wrappedJson);
                                    return;
                                }

                                String result = Utility.refactor(model.getClass(), wrappedJson);
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
