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

    public Mutation() {
        super(null);
    }


    public HashMap<String, ?> getRequestFields() {
        return null;
    }

    @Override
    public String getQuery() {
        String        query = "mutation { %s %s(%s){%s}}";
        StringBuilder args  = new StringBuilder();

        if (getRequestFields() != null) {
            for (HashMap.Entry<String, ?> entry : getRequestFields().entrySet()) {
                args.append(entry.getKey()).append(":").append(entry.getValue());
            }
        }
        return String.format(query,
                getModel() == null ? "" : ":" + getModel().getResponseModelName(),
                getOperationName(),
                args,
                getFields()
        );
    }
}
