package com.github.imanx.QLroid.utility;

import android.support.annotation.Nullable;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtility {

    public static String getStrJson(@Nullable JSONObject jsonObject, String key) {

        if (jsonObject == null) {
            return "";
        }

        JSONObject optJsonObject;

        try {
            jsonObject = jsonObject.getJSONObject("data");

            JSONArray optJSONArray = jsonObject.optJSONArray(key);

            if (optJSONArray != null) {
                return optJSONArray.toString();
            }

            optJsonObject = jsonObject.getJSONObject(key);
            if (optJsonObject != null) {
                return optJsonObject.toString();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";

    }


    public static boolean isJsonObject(String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isJsonArray(String json) {
        try {
            new JSONArray(json);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean existKeyJsonObject(String key, JSONObject json) {
        try {
            json.getJSONObject(key);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean existKeyJsonArray(String key, String json) {
        try {
            JSONArray array = new JSONArray(json);

            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
