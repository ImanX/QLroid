package com.github.imanx.qlroidx;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Argument {

    private List<SubArg> arguments;


    public Argument() {
        arguments = new ArrayList<>();
    }

    public void add(SubArg arg) {
        arguments.add(arg);
    }

    public Raw export() {
        return new Raw(this);
    }


    public static class SubArg {

        private String key;
        private String value;
        private Class  type;

        public SubArg(String key, String value, Class clazz) {
            this.key = key;
            this.value = value;
            this.type = clazz;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public Class<?> getType() {
            return type;
        }
    }


    public class Raw {

        private String argument;
        private String variables;
        private String variablesArgument;

        private Raw(Argument argument) {
            try {
                export();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void export() throws JSONException {

            StringBuilder rawArg = new StringBuilder();
            StringBuilder rawVar = new StringBuilder();
            JSONObject    json   = new JSONObject();

            for (SubArg arg : arguments) {
                rawArg.append(String.format("$%s: %s!", arg.getKey(), arg.getType().getSimpleName()));
                rawVar.append(String.format("%s: $%s", arg.getKey(), arg.getKey()));
                json.put(arg.getKey(), arg.getValue());

                int indexOf = arguments.indexOf(arg);
                if (indexOf != (arguments.size() - 1)) {
                    rawArg.append(",");
                    rawVar.append(",");
                }

            }

            this.argument = String.format("(%s)", rawArg.toString());
            this.variablesArgument = String.format("(%s)", rawVar.toString());
            this.variables = json.toString();
        }


        public String getVariables() {
            return variables;
        }

        public String getArgument() {
            return argument;
        }

        public String getVariablesArgument() {
            return variablesArgument;
        }
    }


}
