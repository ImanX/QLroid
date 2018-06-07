package com.github.imanx.QLroid;

import java.util.HashMap;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public abstract class Mutation extends GraphCore {

    public Mutation(GraphModel model) {
        super(model);
    }

    public abstract HashMap<String, ?> getRequestFields();

    public abstract String getFunctionName();

    @Override
    public String getQuery() {
        String query = "mutation { %s : %s(%s){%s}}";
        String args  = "";

        for (HashMap.Entry<String, ?> entry : getRequestFields().entrySet()) {
            args += entry.getKey() +":" + entry.getValue() + "\n";
        }

        return String.format(query,
                getModel().getResponseModelName(),
                getFunctionName(),
                args,
                getFields()
        );

    }
}
