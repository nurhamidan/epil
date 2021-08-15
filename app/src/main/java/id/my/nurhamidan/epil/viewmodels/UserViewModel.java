package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.my.nurhamidan.epil.exceptions.PasswordRepeatNotMatchException;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.repositories.Repository;
import id.my.nurhamidan.epil.repositories.UserRepository;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository();
        responseLiveData = repository.getResponseMutableLiveData();
    }

    public void login(String username, String password) {
        repository.login(username, password);
    }

    public void create(String nama, String email, String username, String password, String passwordRepeat) throws PasswordRepeatNotMatchException {
        if (password.equals(passwordRepeat)) {
            repository.create(nama, email, username, password);
        } else {
            throw new PasswordRepeatNotMatchException("Password Repeat Is Not Match.");
        }
    }

    public void update(Integer id, String name, String email) {
        repository.update(id, name, email);
    }
}
