package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;
import com.github.imanx.QLroid.annonations.SerializeName;
import com.github.imanx.QLroid.annonations.UnInject;

public class Me extends GraphModel {


    private String email;

    private String avatar;
    @UnInject
    private String userLevelUp;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserLevelUp() {
        return userLevelUp;
    }

    public void setUserLevelUp(String userLevelUp) {
        this.userLevelUp = userLevelUp;
    }

    @SerializeName("fee_group")
    public class FeeGroup extends GraphModel {

        private String name;
        private int    percent;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
    }

}
