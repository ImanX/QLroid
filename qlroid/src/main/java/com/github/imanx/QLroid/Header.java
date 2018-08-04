package com.github.imanx.QLroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Header {


    private HashMap<String, String> map;

    public Header() {
        if (this.map == null) {
            this.map = new HashMap<>();
        }

    }


    public void append(String key, String value) {
        this.map.put(key, value);
    }


    public Map.Entry<String, String> getHeaderEntity(String key) {
        for (Map.Entry<String, String> entry : this.map.entrySet()) {
            if (entry.getKey().equals(key)) return entry;
        }
        return null;
    }


    public HashMap<String, String> getMap() {
        return this.map;
    }
}
