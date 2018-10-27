package ilya.restclient.client;

import java.util.ArrayList;

import ilya.restclient.data.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("users")
    Observable<ArrayList<User>> getUsers();

    @GET("users/{id}")
    Observable<User> getUser(@Path("id") long id);
}
