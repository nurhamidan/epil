package id.my.nurhamidan.epil.apis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST("/users")
    Call<ResponseBody> create(@Field("nama") String nama, @Field("email") String email, @Field("nama_pengguna") String username, @Field("kata_sandi") String password);
}
