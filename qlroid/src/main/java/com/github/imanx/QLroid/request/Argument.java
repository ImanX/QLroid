package com.github.imanx.QLroid.request;

import com.github.imanx.QLroid.argument.Arg;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

        StringBuilder raw = new StringBuilder();


        for (int i = 0; i < args.size(); i++) {
            Arg arg = this.args.get(i);
            String type = arg.getType().getSimpleName().toUpperCase().charAt(0) + arg.getType().getSimpleName().
                    substring(1, arg.getType().getSimpleName().length());

            raw.append(String.format("$%s:%s!", arg.getKey(), type));
            if (i < (this.args.size() - 1)) {
                raw.append(",");
            }
        }
        return raw.toString();
    }

    public String getParameter() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < args.size(); i++) {
            Arg arg = this.args.get(i);
            str.append(String.format("%s:$%s", arg.getKey(), arg.getKey()));
            if (i < (this.args.size() - 1)) {
                str.append(",");
            }
        }
        return str.toString();
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

}