package id.my.nurhamidan.epil.apis;

import java.util.ArrayList;

import id.my.nurhamidan.epil.models.Voting;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VotingService {
    @FormUrlEncoded
    @POST("/votings")
    Call<ResponseBody> create(@Field("nama") String name, @Field("id_pengguna") Integer userId);

    @GET("/votings")
    Call<ArrayList<Voting>> getVotings();

    @DELETE("/votings/{id}")
    Call<ResponseBody> delete(@Path("id") Integer id);
}
