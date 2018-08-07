package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;
import com.github.imanx.QLroid.annonations.UnInject;

public class TicketDepartments extends GraphModel {
    private int     id;
    private String  title;
    @UnInject
    private boolean flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @UnInject
    public class Tickets {
        private String ids;

        public void setIds(String ids) {
            this.ids = ids;
        }

        public String getIds() {
            return ids;
        }
    }

}
