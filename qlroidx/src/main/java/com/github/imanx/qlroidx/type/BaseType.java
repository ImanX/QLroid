package com.github.imanx.qlroidx.type;

import java.lang.*;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class BaseType {

    private java.lang.String name;

    public BaseType(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String toString() {
        return (name != null) ? name : getClass().getSimpleName();
    }
}
