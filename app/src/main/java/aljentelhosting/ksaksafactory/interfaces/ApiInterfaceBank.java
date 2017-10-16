package aljentelhosting.ksaksafactory.interfaces;


import aljentelhosting.ksaksafactory.data.BankJSONResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shirya on 29/08/17.
 */

public interface ApiInterfaceBank {

    @GET("banks/index.php?banks")
    Call<BankJSONResponse> getJSON();
}
