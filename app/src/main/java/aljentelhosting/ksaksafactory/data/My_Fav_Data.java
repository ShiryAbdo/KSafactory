package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 12/09/17.
 */

public class My_Fav_Data {
    private String fav_id;
    private String product_id;
    private String product_title;
    private String product_service;
    private String date_insert;
    public My_Fav_Data(){

    }

    public My_Fav_Data(String fav_id, String product_id, String product_title, String product_service, String date_insert) {
        this.fav_id = fav_id;
        this.product_id = product_id;
        this.product_title = product_title;
        this.product_service = product_service;
        this.date_insert = date_insert;
    }

    public String getFav_id() {
        return fav_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public String getProduct_service() {
        return product_service;
    }

    public String getDate_insert() {
        return date_insert;
    }
}
