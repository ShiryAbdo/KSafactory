package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 12/09/17.
 */

public class Elmonshat_modafa_Data {
    private String company_id;
    private String company_name;
    private String company_image_name;
    public Elmonshat_modafa_Data(){}

    public Elmonshat_modafa_Data(String company_id, String company_name, String company_image_name) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_image_name = company_image_name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCompany_image_name() {
        return company_image_name;
    }
}
