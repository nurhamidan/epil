package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.my.nurhamidan.epil.models.Poll;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.repositories.PollRepository;
import id.my.nurhamidan.epil.repositories.UserRepository;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class PollViewModel extends AndroidViewModel {
    private PollRepository pollRepository;
    private LiveData<Response<ResponseBody>> responseLiveData;

    public PollViewModel(@NonNull Application application) {
        super(application);
        pollRepository = new PollRepository();
        responseLiveData = pollRepository.getResponseMutableLiveData();
    }

    public void create(String name, Integer userId) {
        pollRepository.create(name, userId);
    }

    public LiveData<Response<ResponseBody>> getResponseLiveData() {
        return responseLiveData;
    }
}
