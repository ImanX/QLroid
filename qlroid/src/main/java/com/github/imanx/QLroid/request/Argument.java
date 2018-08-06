package com.github.imanx.QLroid.request;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Argument {
    private HashMap<String, Object> map;

    public Argument() {
        synchronized (this) {
            this.map = new HashMap<>();
        }
    }

    public Argument add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public <E extends Object> E getValue(String key, Class<?> type) {
        return (E) this.map.get(key);
    }

    public String getRaw() {
        StringBuilder raw = new StringBuilder();

        Iterator<Map.Entry<String, Object>> iterator = this.map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            raw.append(String.format("%s:%s", entry.getKey(), entry.getValue()));

            if (iterator.hasNext()) raw.append(",");

        }
        return raw.toString();
    }
}
