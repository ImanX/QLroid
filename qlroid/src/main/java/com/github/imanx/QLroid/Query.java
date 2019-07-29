package com.github.imanx.QLroid;

import org.json.JSONObject;

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

        String query = "query qr %s { %s %s%s%s}";

        JSONObject var         = null;
        String     queryRaw = "";
        String     params      = "";

        String fields = getFields().isEmpty() ? "" : "{" + getFields() + "}";

        if (getModel() != null) {
            fields = getModel().buildQuery(null, "");
        }

        if (getArgument() != null) {
            queryRaw = getArgument().getMutationRaw();
            params = getArgument().getParameter();
            var = getArgument().getQueryRaw();
        }

        setVariables(var);

        if (queryRaw != null && !queryRaw.isEmpty()) {
            queryRaw = "(" + queryRaw + ")";
        } else {
            queryRaw = "";
        }

        if (params != null && !params.isEmpty()) {
            params = "(" + params + ")";
        } else {
            params = "";
        }
        query = String.format(query,
                queryRaw,
                getModel() == null ? "" : String.format("%s :", getModel().getResponseModelName()),
                getOperationName(),
                params,
                fields
        );
        return query;
    }
}