package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 12/09/17.
 */

public class Productes_data {
    private String product_id;
    private String product_title;
    private String product_image_name;
    private String product_service;
    private String username;
    private String date;


    public Productes_data(String product_id, String product_title,
                          String product_image_name, String product_service, String username, String date) {
        this.product_id = product_id;
        this.product_title = product_title;
        this.product_image_name = product_image_name;
        this.product_service = product_service;
        this.username = username;
        this.date = date;
    }

    public Productes_data() {

    }


    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public String getProduct_image_name() {
        return product_image_name;
    }

    public String getProduct_service() {
        return product_service;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }
}
