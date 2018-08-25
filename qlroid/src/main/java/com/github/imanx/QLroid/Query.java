package com.github.imanx.QLroid;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public abstract class Query extends GraphCore {


    public Query(GraphModel element) {
        super(element);
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


        String operationName = getArgument() == null ? getOperationName() : getOperationName()
                + "(" + getArgument().getRaw() + ")";

        return String.format(
                query,
                getModel() == null ? "" : getModel().getResponseModelName() + " : ",
                operationName,
                getFields()
        );
    }
}