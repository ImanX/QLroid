package graphsample.imanx.github.com.comgithubimanxgraphsample;

import com.github.imanx.QLroid.GraphModel;
import com.github.imanx.QLroid.annonations.SerializeName;
import com.github.imanx.QLroid.annonations.UnInject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Me extends GraphModel {

    private String avatar;
    private String email;

    @SerializeName("first_name")
    private String firstName;

    @UnInject
    private String userLevelUp;

    @SerializedName("Addresses")
    @UnInject
    private List<Addresses> addresses;

    @SerializedName("NotificationPreferences")
    @UnInject
    private List<NotificationPreferences> notificationPreferences;


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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setAddresses(List<Addresses> addresses) {
        this.addresses = addresses;
    }

    public List<Addresses> getAddresses() {
        return addresses;
    }

    public void setPreferences(List<NotificationPreferences> preferences) {
        this.notificationPreferences = preferences;
    }

    public List<NotificationPreferences> getPreferences() {
        return notificationPreferences;
    }

    @SerializeName("addresses")
    public class Addresses {

        private String address;
        private String type;
        private String id;
        @SerializeName("landline")
        private String landLine;
        @SerializeName("postal_code")
        private String postalCode;
        private String location;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLandLine() {
            return landLine;
        }

        public void setLandLine(String landLine) {
            this.landLine = landLine;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postal_code) {
            this.postalCode = postal_code;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
    @SerializeName("notification_preferences")
    public class NotificationPreferences {

        private String type;
        private String[] channels;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String[] getChannels() {
            return channels;
        }

        public void setChannels(String[] channels) {
            this.channels = channels;
        }
    }
}
