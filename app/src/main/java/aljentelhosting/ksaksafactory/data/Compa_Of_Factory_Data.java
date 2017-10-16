package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 11/09/17.
 */

public class Compa_Of_Factory_Data {
    private String company_id;
    private String company_name;




    private String company_category_name;



    private String company_image_name;


    public Compa_Of_Factory_Data(String company_id, String company_name, String company_image_name, String company_category_name) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_image_name = company_image_name;
        this.company_category_name = company_category_name;
    }



    public String getCompany_category_name() {
        return company_category_name;
    }

    public String getCompany_image_name() {
        return company_image_name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getCompany_name() {
        return company_name;
    }
}
