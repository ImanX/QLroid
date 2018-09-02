package com.github.imanx.QLroid;

import android.util.Log;

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

        String query       = "mutation mu %s { %s %s(%s){%s}}";
        String mutationRaw = "";
        String params      = "";
        String var = "";

        if (getArgument() != null) {
            mutationRaw = getArgument().getMutationRaw();
            params = getArgument().getParameter();
            var = getArgument().getQueryRaw();
        }

        setVariables(var);

        query = String.format(query,
                "(" + mutationRaw + ")",
                getModel() == null ? "" : ":" + getModel().getResponseModelName(),
                getOperationName(),
                params,
                getFields()
        );
        return query;
    }
}
