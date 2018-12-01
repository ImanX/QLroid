package com.github.imanx.QLroid.argument;

public class Arg {


    private String  key;
    private Object  value;
    private Class   type;
    private boolean optional;

    public Arg(String key, Object value, Class type, boolean optional) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.optional = optional;
    }
    public Arg(String key, Object value, Class type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public Arg(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public void setHasOptional(boolean hasOptional) {
        this.optional = hasOptional;
    }

    public boolean isHasOptional() {
        return optional;
    }
}