package hu.bme.aut.mobsoft.lab.mobsoft.network.user;

import hu.bme.aut.mobsoft.lab.mobsoft.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    /**
     * Login.
     * The endpoint returns information about the login. The response includes the userid in case of a successful login. Otherwise the response is the error code.
     * @param userdata Name and the password of the user.
     * @return Call<BigDecimal>
     */

    @POST("login")
    Call<Integer> loginPost(
            @Body User userdata
    );


}
