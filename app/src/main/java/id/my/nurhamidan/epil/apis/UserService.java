package id.my.nurhamidan.epil.apis;

import id.my.nurhamidan.epil.models.User;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserService extends Service {
    @FormUrlEncoded
    @POST("/login")
    Call<User> login(@Field("nama_pengguna") String username, @Field("kata_sandi") String password);

    @FormUrlEncoded
    @POST("/users")
    Call<ResponseBody> create(@Field("nama") String nama, @Field("email") String email, @Field("nama_pengguna") String username, @Field("kata_sandi") String password);

    @FormUrlEncoded
    @PUT("/users/{id}")
    Call<ResponseBody> update(@Path("id") Integer id, @Field("nama") String name, @Field("email") String email);
}
