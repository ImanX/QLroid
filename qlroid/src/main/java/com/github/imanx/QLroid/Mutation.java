package com.github.imanx.QLroid;


import android.util.Log;

import com.github.imanx.QLroid.request.Argument;

import org.json.JSONObject;


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

        String query = "mutation mu %s { %s %s(%s)%s}";

        JSONObject var         = null;
        String     mutationRaw = "";
        String     params      = "";

        String fields = getFields().isEmpty() ? "" : "{" + getFields() + "}";

        if (getModel() != null) {
            fields = getModel().buildQuery(null, "");
        }

        if (getArgument() != null) {
            mutationRaw = getArgument().getMutationRaw();
            params = getArgument().getParameter();
            var = getArgument().getQueryRaw();
        }

        setVariables(var);

        query = String.format(query,
                "(" + mutationRaw + ")",
                getModel() == null ? "" : String.format("%s :", getModel().getResponseModelName()),
                getOperationName(),
                params,
                fields
        );
        Log.i("AAAA", "getQuery: " + query);
        return query;
    }
}
