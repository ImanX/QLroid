package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;
import com.github.imanx.QLroid.annonations.SerializeName;
import com.github.imanx.QLroid.annonations.UnInject;

import java.util.List;

public class Me extends GraphModel {


    private String email;

    private String avatar;
    @UnInject
    private String userLevelUp;

    @UnInject
    private List<Addresses> addresses;


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

    public void setAddresses(List<Addresses> addresses) {
        this.addresses = addresses;
    }

    public List<Addresses> getAddresses() {
        return addresses;
    }

    @SerializeName("addresses")
    public class Addresses extends GraphModel {

        private String address;
        private String type;

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }
    }
}
