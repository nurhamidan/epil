package id.my.nurhamidan.epil.apis;

import java.util.ArrayList;

import id.my.nurhamidan.epil.models.Candidate;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CandidatesService {
    @GET("/candidates")
    public Call<ArrayList<Candidate>> getCandidates();

    @FormUrlEncoded
    @POST("/candidates")
    public Call<ResponseBody> create(@Field("nama") String name, @Field("id_pemilihan") Integer votingId);

    @DELETE("candidates/{id}")
    public Call<ResponseBody> delete(@Path("id") Integer id);
}
