package aljentelhosting.ksaksafactory.interfaces;


import aljentelhosting.ksaksafactory.data.JSONResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Shaymaa on 6/23/2017.
 */

public interface RequestInterface {

    @GET("company_categories/index.php?company_category")
    Call<JSONResponse> getJSON();
}
