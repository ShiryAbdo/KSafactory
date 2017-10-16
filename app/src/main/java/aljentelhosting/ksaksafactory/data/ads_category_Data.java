package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 18/09/17.
 */

public class ads_category_Data {

    private String ads_categories_id;

    private String ads_categories_name;

    public ads_category_Data(String ads_categories_id, String ads_categories_name) {
        this.ads_categories_id = ads_categories_id;
        this.ads_categories_name = ads_categories_name;
    }

    public String getAds_categories_id() {
        return ads_categories_id;
    }

    public String getAds_categories_name() {
        return ads_categories_name;
    }
}
