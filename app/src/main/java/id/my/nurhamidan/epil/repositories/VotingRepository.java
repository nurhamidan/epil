package id.my.nurhamidan.epil.repositories;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import id.my.nurhamidan.epil.apis.UserService;
import id.my.nurhamidan.epil.apis.VotingService;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.models.Voting;
import id.my.nurhamidan.epil.utils.Constants;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VotingRepository extends Repository {
    private VotingService service;

    public VotingRepository() {
        service = retrofit.create(VotingService.class);
    }

    public void create(String name, Integer userId) {
        service.create(name, userId)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        responseMutableLiveData.postValue(response);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        responseMutableLiveData.postValue(null);
                    }
                });
    }

    public void getVotings() {
        service.getVotings()
                .enqueue(new Callback<ArrayList<Voting>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Voting>> call, Response<ArrayList<Voting>> response) {
                        responseMutableLiveData.postValue(response);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Voting>> call, Throwable t) {
                        responseMutableLiveData.postValue(null);
                    }
                });
    }

    public void delete(Integer id) {
        service.delete(id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        responseMutableLiveData.postValue(response);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        responseMutableLiveData.postValue(null);
                    }
                });
    }
}
