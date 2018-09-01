package com.github.imanx.QLroid;

import com.github.imanx.QLroid.request.Argument;


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


    public Argument getRequestFields() {
        return null;
    }

    @Override
    public String getQuery() {
        String query = "mutation { %s %s(%s){%s}}";
        String args  = getArgument() == null ? "" : getArgument().getMutationRaw();

        setVariables(getArgument() == null ? "" : getArgument().getQueryRaw());

        query = String.format(query,
                getModel() == null ? "" : ":" + getModel().getResponseModelName(),
                getOperationName(),
                args,
                getFields()
        );

        return query;
    }
}
