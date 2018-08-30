package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;
import com.github.imanx.QLroid.annonations.SerializedField;
import com.github.imanx.QLroid.annonations.UnInject;

public class Tickets extends GraphModel {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    @SerializedField("test")
    public class Family {

        @SerializedField("user_me")
        public User user11;

        private String family;

        public void setFamily(String family) {
            this.family = family;
        }

        public String getFamily() {
            return family;
        }
    }


}
