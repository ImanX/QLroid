package com.github.imanx.QLroid;



/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public abstract class Query<T extends GraphModel> extends GraphCore<T> {


    public Query(T element) {
        super(element);
    }

    @Override
    public String getQuery() {

        if (getModel() != null) {
            return "query { " + getModel().buildQuery(getArgument()) + "}";
        }

        String query = "query { %s  %s { %s }}";
        String operationName = getOperationName() == null ?
                getModel().getClass().getSimpleName() : getOperationName();

        operationName = getArgument() == null ? getOperationName() : operationName + "(" + getArgument().getRaw() + ")";
        return String.format(
                query,
                getModel() == null ? "" : getModel().getResponseModelName() + " : ",
                operationName,
                getFields()
        );
    }
}