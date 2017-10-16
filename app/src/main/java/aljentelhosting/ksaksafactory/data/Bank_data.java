package aljentelhosting.ksaksafactory.data;

/**
 * Created by shirya on 29/08/17.
 */

public class Bank_data {

    private String Namebank;
    private String bank_number;
    private String company_name;
    private String bank_ipan;

    public Bank_data(String Namebank, String bank_number, String company_name, String bank_ipan) {
        this.Namebank = Namebank;
        this.bank_number = bank_number;
        this.company_name = company_name;
        this.bank_ipan = bank_ipan;
    }

    public String getBank() {
        return Namebank;

    }

    public void setBank(String bank) {
        this.Namebank = bank;
    }

    public String getBank_number() {
        return bank_number;
    }

    public void setBank_number(String bank_number) {
        this.bank_number = bank_number;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBank_ipan() {
        return bank_ipan;
    }

    public void setBank_ipan(String bank_ipan) {
        this.bank_ipan = bank_ipan;
    }


}
