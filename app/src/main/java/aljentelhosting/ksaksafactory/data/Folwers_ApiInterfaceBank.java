package aljentelhosting.ksaksafactory.data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shirya on 12/09/17.
 */

public interface Folwers_ApiInterfaceBank {

    @GET("banks/index.php?banks")
    Call<BankJSONResponse> getJSON();
}
