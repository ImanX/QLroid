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

        if (getModel() != null && getOperationName() == null) {
            return "query { " + getModel().buildQuery(getArgument()) + "}";
        }

        String query         = "query { %s  %s { %s }}";
        String operationName = getOperationName();
        String responseName  = "";


        if (getArgument() != null) {
            operationName = String.format("(%s)", getArgument().getRaw());
        }


        if (getModel() != null) {
            responseName = String.format("%s :", getModel().getResponseModelName());
        }


        return String.format(
                query,
                responseName,
                operationName,
                getFields()
        );
    }
}