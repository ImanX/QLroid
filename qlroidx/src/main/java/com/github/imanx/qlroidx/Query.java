package com.github.imanx.qlroidx;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public final class Query extends GraphQL {


    public Query(String operationName, Argument argument, FieldQuery fieldQuery) {
        super(operationName, argument, fieldQuery);
    }

    public Query(String operationName, FieldQuery fieldQuery) {
        super(operationName, fieldQuery);
    }

    public Query(String operationName, Argument argument, Object object) {
        super(operationName, argument, object);
    }

    public Query(String operationName, Object object) {
        super(operationName, object);
    }
}
