package id.my.nurhamidan.epil.apis;

import java.util.ArrayList;

import id.my.nurhamidan.epil.models.Votes;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VotesService extends Service {
    @FormUrlEncoded
    @POST("/votes")
    public Call<ResponseBody> create(@Field("id_pengguna") Integer userId, @Field("id_kandidat") Integer candidateId);

    @GET("/votes")
    public Call<ArrayList<Votes>> getVoters();
}
