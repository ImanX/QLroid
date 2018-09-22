package com.github.imanx.qlroidx;

import com.github.imanx.qlroidx.annotation.SerializedField;
import com.github.imanx.qlroidx.annotation.UnInject;

import java.lang.reflect.Field;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class FieldQuery {

    private StringBuilder builder = new StringBuilder();

    public FieldQuery put(String fieldName) {
        builder.append(fieldName).append("\n");
        return this;
    }

    public FieldQuery put(String fieldName, FieldQuery object) {
        builder.append(String.format("%s %s", fieldName, object.toString()));
        return this;
    }

    public FieldQuery put(Object object) {
        builder.append(recycle(object.getClass(), new StringBuilder()));
        return this;
    }


    private String recycle(Class clazz, StringBuilder builder) {


        if (clazz == null) {
            return builder.toString();
        }

        String parentName = clazz.getSimpleName();


        if (clazz.getAnnotation(SerializedField.class) != null) {
            parentName = ((SerializedField) clazz.getAnnotation(SerializedField.class)).value();
        }


        if (clazz.isMemberClass()){
            builder.append(parentName);
            builder.append("{");
        }


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


            if (field.getType().getSuperclass() == Class.class) {
                try {
                    recycle(Class.forName(field.getType().getName()), builder);
                    continue;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }


            builder.append(fieldName).append("\n");


        }

        for (Class child : clazz.getDeclaredClasses()) {
            recycle(child, builder);
        }

        if (clazz.isMemberClass()){
            builder.append("}");
        }

        return recycle(null, builder);


    }


    public void clear() {
        builder.reverse();
    }

    @Override
    public String toString() {
        return String.format("\n%s", builder.toString());
    }
}
