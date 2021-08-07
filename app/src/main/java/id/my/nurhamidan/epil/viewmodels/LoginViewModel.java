package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.my.nurhamidan.epil.models.LoginUser;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.repositories.LoginRepository;
import id.my.nurhamidan.epil.repositories.UserRepository;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private LoginRepository loginRepository;
    private LiveData<Response<LoginUser>> responseLiveData;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository();
        responseLiveData = loginRepository.getResponseMutableLiveData();
    }

    public LiveData<Response<LoginUser>> getResponseLiveData() {
        return responseLiveData;
    }

    public void create(String username, String password) {
        loginRepository.create(username, password);
    }
}
