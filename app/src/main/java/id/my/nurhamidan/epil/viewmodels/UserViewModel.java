package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.repositories.UserRepository;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<Response<User>> responseLiveData;
    private LiveData<Response<ResponseBody>> responsBodyResponseLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
        responseLiveData = userRepository.getResponseMutableLiveData();
        responsBodyResponseLiveData = userRepository.getResponseBodyResponseMutableLiveData();
    }

    public void login(String username, String password) {
        userRepository.login(username, password);
    }

    public void create(String nama, String email, String username, String password) {
        userRepository.create(nama, email, username, password);
    }

    public LiveData<Response<User>> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<Response<ResponseBody>> getResponsBodyResponseLiveData() {
        return responsBodyResponseLiveData;
    }
}
