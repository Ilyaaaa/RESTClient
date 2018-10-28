package ilya.restclient.client;

import java.util.ArrayList;

import ilya.restclient.client.data.ResponseObj;
import ilya.restclient.client.data.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String accessStr = "Authorization: Bearer lr67ETkUcmLucIRN9C78rPvyqLHDsG3xazU8";

    @Headers(accessStr)
    @GET("users")
    Observable<ResponseObj<ArrayList<User>>> getUsers();

    @Headers(accessStr)
    @GET("users/{id}")
    Observable<ResponseObj<User>> getUser(@Path("id") long id);

    @Headers(accessStr)
    @POST("users")
    Observable<ResponseObj<User>> addUser(@Body User user);

}
