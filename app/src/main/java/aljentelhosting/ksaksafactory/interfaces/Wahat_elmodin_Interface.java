package aljentelhosting.ksaksafactory.interfaces;


import aljentelhosting.ksaksafactory.data.Wahat_elmodin_JSONResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shirya on 09/09/17.
 */

public interface Wahat_elmodin_Interface {



        @GET("factories_categories/index3.php?factory_type")
        Call<Wahat_elmodin_JSONResponse> getJSON();

}
