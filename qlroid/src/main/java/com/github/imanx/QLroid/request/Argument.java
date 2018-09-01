package com.github.imanx.QLroid.request;

import android.util.Log;

import com.github.imanx.QLroid.argument.Arg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
            raw.append(String.format("$%s:%s!", arg.getKey(), arg.getType().getSimpleName()));
            if (i < (this.args.size() - 1)) {
                raw.append(",");
            }
        }
        return raw.toString();
    }


    public String getQueryRaw() {
        StringBuilder raw = new StringBuilder();

        for (int i = 0; i < args.size(); i++) {
            Arg arg = this.args.get(i);
            raw.append(String.format("%s:%s", arg.getKey(), arg.getValue()));
            if (i != this.args.size()) {
                raw.append(",");
            }
        }
        return raw.toString();
    }

}