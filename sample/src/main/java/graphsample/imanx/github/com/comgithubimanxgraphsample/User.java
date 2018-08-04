package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class User extends GraphModel {
    public String name;


    @Override
    public String getResponseModelName() {
        return "res";
    }
}
