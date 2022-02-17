package models.usersInfo;

public class Geo {
    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    private String lat;
    private String lng;

}
