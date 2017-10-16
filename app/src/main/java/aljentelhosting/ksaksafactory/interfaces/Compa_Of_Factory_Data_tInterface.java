package aljentelhosting.ksaksafactory.interfaces;


import aljentelhosting.ksaksafactory.data.Compa_Of_Factory_JSONRespons;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shirya on 11/09/17.
 */

public interface Compa_Of_Factory_Data_tInterface {
    @GET("company_by_category/index.php?category=2036")
    Call<Compa_Of_Factory_JSONRespons> getJSON();
}
