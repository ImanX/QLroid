package com.github.imanx.QLroid;

import com.github.imanx.QLroid.annonations.SerializeName;
import com.github.imanx.QLroid.annonations.UnInject;

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

    private String recycle(Class model) {

        Field[] fields = model.getDeclaredFields();

        if (model.getAnnotation(SerializeName.class) != null) {
            builder.append(((SerializeName) model.getAnnotation(SerializeName.class)).value());
        } else {
            builder.append(model.getSimpleName());
        }

        builder.append("{\n");
        for (Field field : fields) {
            if (field.getAnnotation(UnInject.class) != null || field.getType() == this.getClass()) {
                continue;
            }
            builder.append(field.getName()).append("\n");
        }

        Class[] classList = model.getDeclaredClasses();
        for (Class clazz : classList) {
            recycle(clazz);
        }
        builder.append("}\n");
        return builder.toString();
    }


    protected String buildQuery() {
        return recycle(this.getClass());
    }


}
