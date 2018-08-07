package com.github.imanx.QLroid;

import android.support.annotation.Nullable;

import com.github.imanx.QLroid.annonations.SerializeName;
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

    private String recycle(Class classes) {

        if (classes.getAnnotation(UnInject.class) == null) {
            String className;

            if (classes.getAnnotation(SerializeName.class) != null) {
                className = ((SerializeName) classes.getAnnotation(SerializeName.class)).value();
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
                this.builder.append(field.getName()).append("\n");
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
        String query = recycle(this.getClass());
        if (arg != null) {
            return String.format(query, "(" + arg.getRaw() + ")");
        }
        return query.replaceAll("%s", "");
    }
}
