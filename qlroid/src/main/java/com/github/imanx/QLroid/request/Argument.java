package com.github.imanx.QLroid.request;

import com.github.imanx.QLroid.argument.Arg;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Argument {

    private List<Arg> args;

    public Argument() {
        synchronized (this) {
            this.args = new ArrayList<>();
        }
    }

    public void add(Arg... arg) {
        this.args.addAll(Arrays.asList(arg));
    }


    public String getMutationRaw() {

        JSONObject object = new JSONObject();

        for (Arg arg : args) {
            String  name        = arg.getType().getSimpleName();
            String  type        = name.toUpperCase().charAt(0) + name.substring(1, name.length());
            boolean hasOptional = arg.isHasOptional();
            if (type.contains("[]")) {
                type = type.replaceAll("\\[]", "");
                type = "[" + type + "]";
            }


            try {
                object.put("$" + arg.getKey(), type + (hasOptional ? "" : "!"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        String json = object.toString().replaceAll("\"", "");
        json = json.replaceAll("\\{", "");
        return json.replaceAll("\\}", "");
    }

    public String getParameter() {

        JSONObject object = new JSONObject();

        for (Arg arg : args) {
            try {
                object.put(arg.getKey(), " $" + arg.getKey());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        String json = object.toString().replaceAll("\"", "");
        json = json.replaceAll("\\{", "");
        return json.replaceAll("\\}", "");
    }

    public JSONObject getQueryRaw() {

        JSONObject object = new JSONObject();

        for (Arg arg : args) {
            try {
                object.put(arg.getKey(), arg.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    public String getQueryParameter() {

        JSONObject object = new JSONObject();

        for (Arg arg : args) {
            try {
                object.put(arg.getKey(), arg.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String json = object.toString().replaceAll("\"", "");
        json = json.replaceAll("\\{", "");
        return json.replaceAll("\\}", "");
    }

}