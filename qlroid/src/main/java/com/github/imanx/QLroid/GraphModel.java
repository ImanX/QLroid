package com.github.imanx.QLroid;

import android.support.annotation.Nullable;
import android.util.Log;

import com.github.imanx.QLroid.annonations.SerializedField;
import com.github.imanx.QLroid.annonations.UnInject;
import com.github.imanx.QLroid.request.Argument;

import java.lang.reflect.Field;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class GraphModel {


    private StringBuilder builder = new StringBuilder();

    public String getResponseModelName() {
        return this.getClass().getSimpleName();
    }


    private String fetch(Class clazz, StringBuilder builder) {


        if (clazz == null) {
            return builder
                    .append("}")
                    .append("\n")
                    .toString();
        }


        String parentName = clazz.getSimpleName();


        if (clazz.getAnnotation(SerializedField.class) != null) {
            parentName = ((SerializedField) clazz.getAnnotation(SerializedField.class)).value();
        }

        builder.append(parentName);

        if (getClass() == clazz) {
            builder.append("%s");
        }

        builder.append("{\n");

        for (Field field : clazz.getDeclaredFields()) {

            if (field.getAnnotation(UnInject.class) != null) {
                continue;
            }


            if (field.getType() == clazz.getDeclaringClass()) {
                continue;
            }


            String fieldName = field.getName();

            if (field.getAnnotation(SerializedField.class) != null) {
                fieldName = field.getAnnotation(SerializedField.class).value();
            }


            builder
                    .append(fieldName)
                    .append("\n");
        }

        for (Class child : clazz.getDeclaredClasses()) {
            fetch(child, builder);
        }

        return fetch(null, builder);


    }

    private String recycle(Class classes) {

        if (classes.getAnnotation(UnInject.class) == null) {
            String className;

            if (classes.getAnnotation(SerializedField.class) != null) {
                className = ((SerializedField) classes.getAnnotation(SerializedField.class)).value();
            } else {
                className = classes.getSimpleName();
            }

            this.builder.append(className);

            if (this.getClass() == classes) {
                this.builder.append("%s");
            }

            this.builder.append("{\n");

            Field[] fields = classes.getDeclaredFields();

            for (Field field : fields) {
                if (field.getAnnotation(UnInject.class) != null || field.getType() == this.getClass()) {
                    continue;
                }

                String fieldName;

                if (field.getAnnotation(SerializedField.class) != null) {
                    fieldName = (field.getAnnotation(SerializedField.class)).value();
                } else {
                    fieldName = field.getName();
                }
                this.builder.append(fieldName).append("\n");
            }
        }

        Class[] classList = classes.getDeclaredClasses();
        for (Class clazz : classList) {
            recycle(clazz);
        }

        if (classes.getAnnotation(UnInject.class) == null) {
            this.builder.append("}\n");
        }
        return this.builder.toString();
    }

    protected String buildQuery(@Nullable Argument arg) {
        String query = fetch(this.getClass(), new StringBuilder());
        if (arg != null) {
            return String.format(query, "(" + arg.getRaw() + ")");
        }
        return query.replaceAll("%s", "");
    }
}
