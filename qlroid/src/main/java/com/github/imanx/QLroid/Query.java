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

        String query         = "query { %s  %s { %s }}";
        String operationName = getOperationName();
        String responseName  = "";

        if (getModel() != null) {
            responseName = String.format("%s :", getModel().getResponseModelName());
        }


        if (getModel() != null) {
            return "query { " + getModel().buildQuery(getArgument(), responseName + "" + getOperationName()) + "}";
        }


        if (getArgument() != null) {
            operationName = String.format("(%s)", getArgument().getQueryRaw());
        }

        return String.format(
                query,
                responseName,
                operationName,
                getFields()
        );
    }
}