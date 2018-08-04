package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;

public class CardModel extends GraphModel {

    private int cardId;

    public CardModel(int cardId){
        this.cardId = cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardId() {
        return cardId;
    }
}
