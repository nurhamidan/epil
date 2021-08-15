package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;

import id.my.nurhamidan.epil.repositories.VotesRepository;

public class VotesViewModel extends ViewModel {
    private VotesRepository repository;
    public VotesViewModel(@NonNull Application application) {
        super(application);
        repository = new VotesRepository();
        responseLiveData = repository.getResponseMutableLiveData();
    }

    public void create(Integer userId, Integer candidateId) {
        repository.create(userId, candidateId);
    }

    public void getVoters() {
        repository.getVoters();
    }
}
