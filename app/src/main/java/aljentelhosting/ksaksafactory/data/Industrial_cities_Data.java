package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 31/08/17.
 */

public class Industrial_cities_Data {


    private String factory_id;
    private String factory_title;
    private String factory_space;
    private String city_name;

    public String getFactory_image() {
        return factory_image;
    }

    private String factory_image;
    private String content;
    private String date_insert;
    private String date_update;

    public Industrial_cities_Data(String factory_id, String factory_title, String factory_space, String city_name, String content, String date_insert, String date_update) {
        this.factory_id = factory_id;
        this.factory_title = factory_title;
        this.factory_space = factory_space;
        this.city_name = city_name;
        this.content = content;
        this.date_insert = date_insert;
        this.date_update = date_update;
    }

    public String getFactory_id() {
        return factory_id;
    }

    public String getFactory_title() {
        return factory_title;
    }

    public String getFactory_space() {
        return factory_space;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getContent() {
        return content;
    }

    public String getDate_insert() {
        return date_insert;
    }

    public String getDate_update() {
        return date_update;
    }
}

