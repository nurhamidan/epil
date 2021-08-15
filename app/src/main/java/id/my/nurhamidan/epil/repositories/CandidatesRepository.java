package id.my.nurhamidan.epil.repositories;

import java.util.ArrayList;

import id.my.nurhamidan.epil.apis.CandidatesService;
import id.my.nurhamidan.epil.models.Candidate;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidatesRepository extends Repository {
    private CandidatesService service;

    public CandidatesRepository() {
        this.service = retrofit.create(CandidatesService.class);
    }

    public void getCandidates() {
        service.getCandidates()
                .enqueue(new Callback<ArrayList<Candidate>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Candidate>> call, Response<ArrayList<Candidate>> response) {
                        responseMutableLiveData.postValue(response);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Candidate>> call, Throwable t) {
                        responseMutableLiveData.postValue(null);
                    }
                });
    }

    public void create(String name, Integer votingId) {
        service.create(name, votingId)
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
