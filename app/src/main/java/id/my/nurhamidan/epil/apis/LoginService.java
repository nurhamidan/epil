package id.my.nurhamidan.epil.apis;

import id.my.nurhamidan.epil.models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST("/login")
    Call<User> login(@Field("nama_pengguna") String username, @Field("kata_sandi") String password);
}
