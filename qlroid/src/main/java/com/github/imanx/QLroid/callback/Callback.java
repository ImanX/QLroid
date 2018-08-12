package com.github.imanx.QLroid.callback;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public interface Callback<T> {
    void onResponse(T response);

    void onFailure();
}
