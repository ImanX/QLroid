package com.github.imanx.QLroid;

import android.support.annotation.Nullable;

import com.github.imanx.QLroid.annonations.SerializedField;
import com.github.imanx.QLroid.annonations.UnInject;
import com.github.imanx.QLroid.request.Argument;

import java.lang.reflect.Field;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class GraphModel {

    public String getResponseModelName() {
        return this.getClass().getSimpleName();
    }


    private String recycle(Class clazz, StringBuilder builder, @Nullable String opt) {


        if (clazz == null) {
            return builder
                    .append("}")
                    .append("\n")
                    .toString();
        }
        String parentName = clazz.getSimpleName();
        if (opt != null) {
            parentName = opt;
        }


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


            if (field.getType().getSuperclass() == GraphModel.class) {
                try {
                    recycle(Class.forName(field.getType().getName()), builder, fieldName);
                    continue;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }


            builder
                    .append(fieldName)
                    .append("\n");
        }

        for (Class child : clazz.getDeclaredClasses()) {
            recycle(child, builder, null);
        }

        return recycle(null, builder, null);


    }

    protected String buildQuery(@Nullable Argument arg, @Nullable String operationName) {
        String query = recycle(this.getClass(), new StringBuilder(), operationName);
        if (arg != null) {
            return String.format(query, "(" + arg.getQueryRaw() + ")");
        }
        return query.replaceAll("%s", "");
    }
}
