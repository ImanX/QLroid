package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;
import com.github.imanx.QLroid.annonations.UnInject;

public class CardModel extends GraphModel {

    private int    id;
    private String pan;

    @UnInject
    private String fullName;

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

    public class CardStatus extends GraphModel {
        private String status;
        private String name;

        public void setStatus(String status) {
            this.status = status;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }

        public class CardItem extends GraphModel {
            private String  item;
            private boolean cardFlag;

            public void setCardFlag(boolean cardFlag) {
                this.cardFlag = cardFlag;
            }

            public void setItem(String item) {
                this.item = item;
            }

            public String getItem() {
                return item;
            }

            public boolean getCardStatus() {
                return this.cardFlag;
            }


            public class EndClass extends GraphModel {
                public double endField;

                public void setEndField(double endField) {
                    this.endField = endField;
                }

                public double getEndField() {
                    return endField;
                }
            }

        }
    }
}
