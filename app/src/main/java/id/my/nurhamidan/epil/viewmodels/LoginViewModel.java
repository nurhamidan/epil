package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.my.nurhamidan.epil.models.LoginUser;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.repositories.LoginUserRepository;
import id.my.nurhamidan.epil.repositories.Repository;
import id.my.nurhamidan.epil.repositories.UserRepository;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<Response> responseLiveData;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
        responseLiveData = userRepository.getResponseMutableLiveData();
    }

    public LiveData<Response> getResponseLiveData() {
        return responseLiveData;
    }

    public void login(String username, String password) {
        userRepository.login(username, password);
    }
}
