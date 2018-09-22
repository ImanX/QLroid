package com.github.imanx.qlroidx;

import com.github.imanx.qlroidx.annotation.SerializedField;
import com.github.imanx.qlroidx.annotation.UnInject;

import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by ImanX.
 * QLroidX | Copyrights 2018 ZarinPal Crop.
 */

public class Utils {

    private String refactorJson;


    public static String refactor(Class clazz, String content) {
        return new Utils().iteratorFieldClass(clazz, content);
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
}
