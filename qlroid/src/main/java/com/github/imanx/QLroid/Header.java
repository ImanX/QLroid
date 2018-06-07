package com.github.imanx.QLroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Header {


    private List<Entity> list;

    public Header() {
        this.list = new ArrayList<>();
    }

    public void append(Entity entity) {
        this.list.add(entity);
    }

    public List<Entity> getEntity() {
        return this.list;
    }


    public HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        for (Entity entity : list){
            map.put(entity.getField() , entity.getDescription());
        }

        return map;
    }

    public static class Entity {

        private String field;
        private String description;

        public Entity(String field, String description) {
            this.description = description;
            this.field = field;
        }


        public String getField() {
            return field;
        }


        public String getDescription() {
            return description;
        }


    }
}
