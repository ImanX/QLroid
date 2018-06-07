package com.github.imanx.QLroid;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Query extends GraphCore {

    public Query(GraphModel model) {
        super(model);
    }

    public Query() {
        super(null);

    }


    public String getOperationName() {
        return null;
    }


    @Override
    public String getQuery() {
        String query = "query { %s  %s { %s }}";

        return String.format(
                query,
                getModel() == null ? "" : getModel().getClass().getSimpleName() + " : ",
                getOperationName() == null ? getModel().getResponseModelName() : getOperationName(),
                getFields()
        );
    }


}
