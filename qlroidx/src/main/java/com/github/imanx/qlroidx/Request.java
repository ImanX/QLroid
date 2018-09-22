package com.github.imanx.qlroidx;


import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Request {

    private OkHttpClient.Builder    client;
    private okhttp3.Request.Builder request;
    private GraphQL                 graphQL;

    public Request(String URL, GraphQL graphQL) {
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), graphQL.getBody());
            this.request = new okhttp3.Request.Builder()
                    .post(body)
                    .url(URL);


            Log.i("TAG", "Request: " + graphQL.getBody());

            this.graphQL = graphQL;
            this.client = new OkHttpClient().newBuilder();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public Request setTimeout(int second) {
        this.client.connectTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    public Request addHeader(String name, String value) {
        this.request.addHeader(name, value);
        return this;
    }

    public <T> void enqueue(final GraphCallback<T> callback) {
        this.client.build().newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(Request.this, null, 0, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                String responseBody = response.body().string();


                if (!response.isSuccessful()) {
                    callback.onFailure(Request.this, responseBody, response.code(), new Exception(responseBody));
                    return;
                }

                String responseParsed = null;

                try {
                    responseParsed = new JSONObject(responseBody)
                            .getJSONObject("data")
                            .get(graphQL.getAlias()).toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (callback.getType() == null) {
                    callback.onResponse(Request.this, responseBody, null);
                    return;
                }

                if (graphQL.getObject() != null) {
                    responseParsed = Utils.refactor(graphQL.getObject().getClass(), responseParsed);
                } else {

                    String className = callback.getType()
                            .toString()
                            .replace("class", "")
                            .trim();
                    try {
                        Class tClass = Class.forName(className);
                        T     t      = (T) tClass.newInstance();
                        responseParsed = Utils.refactor(t.getClass(), responseParsed);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }

                }


//                    JSONObject object = new JSONObject(responseParsed);
//                    for (Iterator<String> i = object.keys(); i.hasNext(); ) {
//                        String key = i.next();
//                        for (Field f : t.getClass().getDeclaredFields()) {
//                            String name  = f.getName();
//                            String nameL = f.getName().toLowerCase();
//                                if (nameL.contains(key)) {
//                                responseParsed = responseParsed.replaceAll(key, f.getName());
//                            }
//                        }
//                    }


//                    for (Iterator<String> it = object.keys(); it.hasNext(); ) {
//                        String key = it.next();
//                        for (Field f : t.getClass().getDeclaredFields()) {
//                            if (key.contains(f.getName())) {
//                                responseParsed = responseParsed.replaceAll(f.getName(), key);
//                            }
//                        }
//
//
//                    }


                Object object = new GsonBuilder().create().fromJson(responseParsed, callback.getType());
                callback.onResponse(Request.this, null, (T) object);

            }
        });

    }


}
