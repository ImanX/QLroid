package com.github.imanx.QLroid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public abstract class GraphCore {

    private GraphModel model;

    public GraphCore(GraphModel model) {
        this.model = model;
    }

    protected GraphModel getModel() {
        return this.model;
    }

    public abstract String getQuery();


    public String[] getResponseFields() {
        return null;
    }


    public String getFields() {
        if (getResponseFields() != null) {
            return wrap(Arrays.asList(getResponseFields()));
        }

        List<Field>  fields = Arrays.asList(getModel().getClass().getDeclaredFields());
        List<String> result = new ArrayList<>();
        for (Field field : fields) {
            result.add(field.getName());
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
