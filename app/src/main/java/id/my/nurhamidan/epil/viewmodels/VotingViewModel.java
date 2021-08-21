package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.models.Voting;
import id.my.nurhamidan.epil.repositories.VotingRepository;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class VotingViewModel extends ViewModel {
    private VotingRepository repository;

    public VotingViewModel(@NonNull Application application) {
        super(application);
        repository = new VotingRepository();
        responseLiveData = repository.getResponseMutableLiveData();
    }

    public void create(String name, Integer userId) {
        repository.create(name, userId);
    }

    public void getVotings() {
        repository.getVotings();
    }

    public void deleteVoting(Integer votingId) {
        repository.delete(votingId);
    }
}
