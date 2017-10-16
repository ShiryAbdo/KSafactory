package aljentelhosting.ksaksafactory.interfaces;


import aljentelhosting.ksaksafactory.data.search_company_JSONResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shirya on 11/09/17.
 */

public interface search_company_RequestInterface {
    @GET("search_company/index.php?company_id=1")
    Call<search_company_JSONResponse> getJSON();
}

