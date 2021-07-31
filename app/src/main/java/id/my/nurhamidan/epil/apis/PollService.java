package id.my.nurhamidan.epil.apis;

import id.my.nurhamidan.epil.models.Poll;
import id.my.nurhamidan.epil.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PollService {
    @FormUrlEncoded
    @POST("/polls")
    Call<ResponseBody> create(@Field("nama") String name, @Field("id_pengguna") Integer userId);
}
