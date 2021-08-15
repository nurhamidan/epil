package id.my.nurhamidan.epil.repositories;

import java.util.ArrayList;

import id.my.nurhamidan.epil.apis.VotesService;
import id.my.nurhamidan.epil.models.Votes;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VotesRepository extends Repository {
    private VotesService service;

    public VotesRepository() {
        service = retrofit.create(VotesService.class);
    }

    public void create(Integer userId, Integer candidateId) {
        service.create(userId, candidateId)
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

    public void getVoters() {
        service.getVoters()
                .enqueue(new Callback<ArrayList<Votes>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Votes>> call, Response<ArrayList<Votes>> response) {
                        responseMutableLiveData.postValue(response);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Votes>> call, Throwable t) {
                        responseMutableLiveData.postValue(null);
                    }
                });
    }
}
