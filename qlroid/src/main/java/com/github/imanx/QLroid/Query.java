package com.github.imanx.QLroid;


import android.util.Log;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public abstract class Query extends GraphCore {

    public Query(GraphModel model) {
        super(model);
    }

    public Query() {
        super(null);
    }

    @Override
    public String getQuery() {

        if (getModel() != null) {
            return "query { " + getModel().buildQuery(getArgument()) + "}";
        }

        String query = "query { %s  %s { %s }}";
        String operationName = getOperationName() == null ?
                getModel().getClass().getSimpleName() : getOperationName();

        operationName = getArgument() == null ? "" : operationName + "(" + getArgument().getRaw() + ")";

        return String.format(
                query,
                getModel() == null ? "" : getModel().getResponseModelName() + " : ",
                operationName,
                getFields()
        );
    }


}