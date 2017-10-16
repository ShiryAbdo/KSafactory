package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 29/08/17.
 */

public class e3lanatData {
    private String ads_id;
    private String username;
    private String ads_title;
    private String ads_image_name;
    private String ads_date_insert;

    public e3lanatData(String ads_id, String username, String ads_title, String ads_image_name, String ads_date_insert) {
        this.ads_id = ads_id;
        this.username = username;
        this.ads_title = ads_title;
        this.ads_image_name = ads_image_name;
        this.ads_date_insert = ads_date_insert;
    }

    public String getAds_id() {
        return ads_id;
    }

    public void setAds_id(String ads_id) {
        this.ads_id = ads_id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAds_title() {
        return ads_title;
    }

    public void setAds_title(String ads_title) {
        this.ads_title = ads_title;
    }

    public String getAds_image_name() {
        return ads_image_name;
    }

    public void setAds_image_name(String ads_image_name) {
        this.ads_image_name = ads_image_name;
    }

    public String getAds_date_insert() {
        return ads_date_insert;
    }

    public void setAds_date_insert(String ads_date_insert) {
        this.ads_date_insert = ads_date_insert;
    }
}
