package com.github.imanx.QLroid.callback;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public interface Callback {
    void onResponse(String response);

    void onFailure(int httpCode, String data);
}
