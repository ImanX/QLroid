package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;
import com.github.imanx.QLroid.annonations.UnInject;

public class Tickets extends GraphModel {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @UnInject
    public class Name {
        private String test;

        public void setTest(String test) {
            this.test = test;
        }

        public String getTest() {
            return test;
        }
    }

    @UnInject
    public class Family {
        private String family;

        public void setFamily(String family) {
            this.family = family;
        }

        public String getFamily() {
            return family;
        }
    }
}
