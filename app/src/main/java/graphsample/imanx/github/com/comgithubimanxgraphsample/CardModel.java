package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;

public class CardModel extends GraphModel {

    private int    id;
    private String pan;

    public CardModel(int id, String pan) {
        this.id = id;
        this.pan = pan;
    }

    public CardModel() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPan() {
        return pan;
    }

    @Override
    public String getResponseModelName() {
        return "myCards";
    }
}
