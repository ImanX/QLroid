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

        String query = "query { %s  %s { %s }}";
        // implementation reflection query
        String queryString = "query { "+ getModel().recyclerClasess(getModel().getClass()) + "}";
        return String.format(
                query,
                getModel() == null ? "" : getModel().getResponseModelName() + " : ",
                getOperationName() == null ? getModel().getClass().getSimpleName() : getOperationName(),
                getFields()
        );
    }



}