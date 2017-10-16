package aljentelhosting.ksaksafactory.data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shirya on 18/09/17.
 */

public interface  RequestInterface_AddCatogery {
    @GET("ads_categories/index.php?ads_category")
    Call<JSONResponse_AddCatogery> getJSON();


}
