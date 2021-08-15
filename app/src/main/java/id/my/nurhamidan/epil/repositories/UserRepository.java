package id.my.nurhamidan.epil.repositories;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;

import id.my.nurhamidan.epil.apis.LoginUserService;
import id.my.nurhamidan.epil.apis.Service;
import id.my.nurhamidan.epil.apis.UserService;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.utils.Constants;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository extends Repository {
    private UserService service;

    public UserRepository() {
        service = retrofit.create(UserService.class);
    }

    public void create(String nama, String email, String username, String password) {
        service.create(nama, email, username, password)
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

    public void login(String username, String password) {
        service.login(username, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        responseMutableLiveData.postValue(response);
                        //System.out.println("println " + response.code());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        responseMutableLiveData.postValue(null);
                        //System.out.println("println " + "gagal");
                    }
                });
    }

    public void update(Integer id, String name, String email) {
        service.update(id, name, email)
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
