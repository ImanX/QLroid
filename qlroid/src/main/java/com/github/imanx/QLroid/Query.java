package com.github.imanx.QLroid;


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
            return "query { " + getModel().buildQuery() + "}";
        }
        String query = "query { %s  %s { %s }}";
        return String.format(
                query,
                getModel() == null ? "" : getModel().getResponseModelName() + " : ",
                getOperationName() == null ? getModel().getClass().getSimpleName() : getOperationName(),
                getFields()
        );
    }


}