package com.github.imanx.QLroid;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.zarinpal.libs.httpRequest.HttpRequest;
import com.zarinpal.libs.httpRequest.OnCallbackRequestListener;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Request {

    private Builder  builder;
    private Callback callback;

    private Request(Builder builder) {
        this.builder = builder;
    }


    public void enqueue(Callback callback) {
        this.callback = callback;
        enqueue();
    }

    public void enqueue() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("TAG", "run: "+builder.graphCore.getQuery());

//                try {
//
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(builder.getUri().toString()).openConnection();
//                    httpURLConnection.setRequestMethod("POST");
//                    httpURLConnection.setDoOutput(true);
//                    httpURLConnection.setDoInput(true);
//                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
//                    httpURLConnection.setRequestProperty("Accept", "application/json");
//                    // httpURLConnection.setRequestProperty("Authorization", "bearer 89c084087df48df1d2d42e561bcd941936743926");
//
//
//                    if (builder.getHeader() != null) {
//                        for (Header.Entity entity : builder.getHeader().getEntity()) {
//                            httpURLConnection.addRequestProperty(entity.getField(), entity.getDescription());
//                        }
//                    }
//
//                    httpURLConnection.connect();
//
//
//
//                    JSONObject jsonObject = new JSONObject();
//                    try {
//                        jsonObject.put("operationName", null);
//                        jsonObject.put("query", builder.getGraphCore().getQuery());
//                        jsonObject.put("variable", "{}");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    String str = jsonObject.toString();
//                    Log.i("TAG", "run: " + str);
//                    byte[]       outputInBytes = str.getBytes("UTF-8");
//                    OutputStream os            = httpURLConnection.getOutputStream();
//                    os.write(outputInBytes);
//                    os.close();
//
//
//                    int a = httpURLConnection.getResponseCode();
//                    Log.i("TAG", "run: " + a);
//
//
////                    BufferedReader reader  = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
////                    StringBuilder  builder = new StringBuilder();
////                    String         lines;
////                    while ((lines = reader.readLine()) != null) {
////                        builder.append(lines + "\n");
////                    }
//
//                    BufferedReader reader  = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//                    StringBuilder  builder = new StringBuilder();
//                    String         lines;
//                    while ((lines = reader.readLine()) != null) {
//                        builder.append(lines + "\n");
//                    }
//
//
//                    Log.i("TAG", "run: " + builder.toString());
//
//                    reader.close();
//
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//


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
                                if (callback != null) callback.onResponse(content);
                            }

                            @Override
                            public void onFailureResponse(int httpCode, String dataError) {
                                Log.i("TAG", "onFailureResponse: "+httpCode+ " || "+dataError);
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
            this.context =context;
        }

        public Builder(Context context, Uri uri, Mutation mutation) {
            this.uri = uri;
            this.graphCore = mutation;
            this.context = context;
        }

        public Builder setHeader(Header header) {
            this.header = header;
            return this;
        }

        public Builder setTimeout(int sec) {
            this.timeout = sec;
            return this;
        }

        public GraphCore getGraphCore() {
            return graphCore;
        }

        public Header getHeader() {
            return header;
        }

        public Uri getUri() {
            return uri;
        }

        public Context getContext() {
            return context;
        }

        public Request build() {
            return new Request(this);
        }


        public int getTimeout() {
            return timeout;
        }
    }


}
