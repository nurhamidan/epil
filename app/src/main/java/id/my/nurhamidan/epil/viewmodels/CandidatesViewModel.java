package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;

import id.my.nurhamidan.epil.repositories.CandidatesRepository;

public class CandidatesViewModel extends ViewModel {
    private CandidatesRepository repository;
    public CandidatesViewModel(@NonNull Application application) {
        super(application);
        repository = new CandidatesRepository();
        responseLiveData = repository.getResponseMutableLiveData();
    }

    public void getCandidates() {
        repository.getCandidates();
    }

    public void create(String name, Integer votingId) {
        repository.create(name, votingId);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
