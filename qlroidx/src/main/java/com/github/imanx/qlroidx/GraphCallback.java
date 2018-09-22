package com.github.imanx.qlroidx;

import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public interface GraphCallback<T> {
    void onResponse(Request request, String response, T t);

    void onFailure(Request request, String response, int status, Exception ex);

    Type getType();
}
