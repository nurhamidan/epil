package id.my.nurhamidan.epil.repositories;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;

import id.my.nurhamidan.epil.apis.LoginUserService;
import id.my.nurhamidan.epil.models.LoginUser;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.utils.Constants;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginUserRepository {

    private MutableLiveData<Response<User>> responseMutableLiveData;
    private LoginUserService loginUserService;

    public LoginUserRepository() {
        responseMutableLiveData = new MutableLiveData<>();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .header("Authorization", Credentials.basic(Constants.AUTH_USERNAME, Constants.AUTH_PASSWORD))
                        .build();
                return chain.proceed(request);
            }
        }).build();

        loginUserService = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginUserService.class);
    }

    public MutableLiveData<Response<User>> getResponseMutableLiveData() {
        return responseMutableLiveData;
    }

    public void login(String username, String password) {
        loginUserService.login(username, password)
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
}
