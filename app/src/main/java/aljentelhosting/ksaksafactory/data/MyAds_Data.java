package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 12/09/17.
 */

public class MyAds_Data {

    private String ads_id;
    private String ads_title;
    private String ads_category_name;
    private String ads_price;
    private String ads_image_name;
    private String ads_city_name;

    public MyAds_Data(){

    }
    public MyAds_Data(String ads_id, String ads_title, String ads_category_name, String ads_price, String ads_image_name, String ads_city_name) {
        this.ads_id = ads_id;
        this.ads_title = ads_title;
        this.ads_category_name = ads_category_name;
        this.ads_price = ads_price;
        this.ads_image_name = ads_image_name;
        this.ads_city_name = ads_city_name;
    }

    public String getAds_id() {
        return ads_id;
    }

    public String getAds_title() {
        return ads_title;
    }

    public String getAds_category_name() {
        return ads_category_name;
    }

    public String getAds_price() {
        return ads_price;
    }

    public String getAds_image_name() {
        return ads_image_name;
    }

    public String getAds_city_name() {
        return ads_city_name;
    }
}
