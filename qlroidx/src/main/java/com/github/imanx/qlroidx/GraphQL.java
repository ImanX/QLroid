package com.github.imanx.qlroidx;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public abstract class GraphQL {

    private String     operationName;
    private Argument   argument;
    private FieldQuery fieldQuery;
    private String     alias;
    private Object     object;


    public GraphQL(String operationName, Argument argument, FieldQuery fieldQuery) {
        this.operationName = operationName;
        this.argument = argument;
        this.fieldQuery = fieldQuery;
    }

    public GraphQL(String operationName, FieldQuery fieldQuery) {
        this(operationName, null, fieldQuery);
    }

    public GraphQL(String operationName, Argument argument, Object object) {
        this(operationName, argument, new FieldQuery().put(object));
        this.object = object;

    }

    public GraphQL(String operationName, Object object) {
        this(operationName, null, new FieldQuery().put(object));
        this.object = object;

    }


    public void setAlias(String alias) {
        this.alias = alias;
    }

    private String getQueryType() {
        return getClass().getSimpleName().toLowerCase();
    }

    public String getAlias() {
        return alias;
    }

    private String buildQuery() {

        Argument.Raw raw = (argument != null) ? argument.export() : null;


        StringBuilder result     = new StringBuilder();
        StringBuilder sectionOne = new StringBuilder();
        StringBuilder sectionTwo = new StringBuilder();


        sectionOne.append(String.format("%s %s ", getQueryType(), operationName.toUpperCase()));

        if (raw != null) {
            sectionOne.append(raw.getArgument());
        }


        if (alias == null) {
            alias = operationName;
        }

        sectionTwo.append(String.format("%s : %s", alias, operationName));


        if (raw != null) {
            sectionTwo.append(raw.getVariablesArgument());
        }


        result.append(String.format("%s{ \n%s{%s}}", sectionOne, sectionTwo, fieldQuery.toString()));
        return result.toString();
    }

    public String getBody() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("query", buildQuery());
        jsonObject.put("variables", argument == null ? null : argument.export().getVariables());


        return jsonObject.toString();
    }


    public Object getObject() {
        return object;
    }

    public FieldQuery getFieldQuery() {
        return fieldQuery;
    }
}
