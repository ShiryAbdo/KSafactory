package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 12/09/17.
 */

public class Folwers_Data {

     private String user_id;
    private String username;
    private String date_insert;


    public Folwers_Data(String user_id, String username, String date_insert) {
         this.user_id = user_id;
        this.username = username;
        this.date_insert = date_insert;
    }

    public Folwers_Data() {

    }


    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getDate_insert() {
        return date_insert;
    }
}
