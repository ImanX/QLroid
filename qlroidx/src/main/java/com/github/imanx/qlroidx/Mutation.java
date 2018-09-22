package com.github.imanx.qlroidx;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public final class Mutation extends GraphQL {

    public Mutation(String operationName, Argument argument, FieldQuery fieldQuery) {
        super(operationName, argument, fieldQuery);
    }

    public Mutation(String operationName, FieldQuery fieldQuery) {
        super(operationName, fieldQuery);
    }

    public Mutation(String operationName, Argument argument, Object object) {
        super(operationName, argument, object);
    }

    public Mutation(String operationName, Object object) {
        super(operationName, object);
    }
}
