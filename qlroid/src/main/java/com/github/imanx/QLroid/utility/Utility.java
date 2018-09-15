package com.github.imanx.QLroid.utility;

import android.support.annotation.Nullable;

import com.github.imanx.QLroid.annonations.SerializedField;
import com.github.imanx.QLroid.annonations.UnInject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class Utility {


    private String refactorJson;


    @Nullable
    public static String getWrappedJson(@Nullable JSONObject jsonObject, @Nullable String key) {

        if (jsonObject == null) return null;


        try {
            jsonObject = jsonObject.getJSONObject("data");

            if (key == null) {
                return jsonObject.toString();
            }

            JSONArray  optJSONArray  = jsonObject.optJSONArray(key);
            JSONObject optJsonObject = jsonObject.optJSONObject(key);


            if (optJSONArray != null) {
                return optJSONArray.toString();
            }

            if (optJsonObject != null) {
                return optJsonObject.toString();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }


    public static String refactor(Class clazz, String content) {
        return new Utility().iteratorFieldClass(clazz, content);
    }


    private String iteratorFieldClass(Class clazz, String json) {

        this.refactorJson = json;
        iteratorFields(clazz, json);

        Class[] classList = clazz.getDeclaredClasses();

        for (Class myClass : classList) {

            if (myClass.getAnnotation(UnInject.class) != null) {
                continue;
            }
            iteratorFields(myClass, json);
        }
        return this.refactorJson;
    }

    private void iteratorFields(Class clazz, String json) {

        if (clazz.getAnnotation(SerializedField.class) != null) {
            String temp = ((SerializedField) clazz.getAnnotation(SerializedField.class)).value();
            if (json.contains(temp)) {
                this.refactorJson = this.refactorJson.replaceAll(temp, clazz.getSimpleName());
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
                    this.refactorJson = this.refactorJson.replaceAll(temp, fieldName);
                }
            }
        }
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
