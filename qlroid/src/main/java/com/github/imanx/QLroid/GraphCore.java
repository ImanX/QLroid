package com.github.imanx.QLroid;


import android.support.annotation.Nullable;

import com.github.imanx.QLroid.annonations.SerializedField;
import com.github.imanx.QLroid.annonations.UnInject;
import com.github.imanx.QLroid.request.Argument;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public abstract class GraphCore {

    private GraphModel element;

    public GraphCore(GraphModel element) {
        this.element = element;
    }

    public GraphCore() {
    }

    public GraphModel getModel() {
        return this.element;
    }

    public abstract String getQuery();

    @Nullable
    public abstract String getOperationName();


    public String[] getResponseFields() {
        return null;
    }

    public Argument getArgument() {
        return null;
    }

    public String getFields() {

        if (getResponseFields() != null) {
            return wrap(Arrays.asList(getResponseFields()));
        }
        if (getModel() == null) {
            return "";
        }

        //TODO this needs a Illegal Exception.

        List<Field>  fields = Arrays.asList(getModel().getClass().getDeclaredFields());
        List<String> result = new ArrayList<>();
        for (Field field : fields) {
            if (field.getAnnotation(UnInject.class) != null) {
                continue;
            }
            String fieldName = field.getName();
            if (field.getAnnotation(SerializedField.class) != null) {
                fieldName = (field.getAnnotation(SerializedField.class)).value();
            }
            result.add(fieldName);
        }

        return wrap(result);
    }


    private String wrap(List<String> list) {
        return list
                .toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "\n");
    }
}
