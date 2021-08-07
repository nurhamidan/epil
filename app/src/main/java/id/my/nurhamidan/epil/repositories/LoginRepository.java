package id.my.nurhamidan.epil.repositories;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import id.my.nurhamidan.epil.apis.LoginService;
import id.my.nurhamidan.epil.apis.UserService;
import id.my.nurhamidan.epil.models.LoginUser;
import id.my.nurhamidan.epil.models.User;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository {
    private static final String BASE_URL = "https://api-epil.nurhamidan.my.id/";

    private MutableLiveData<Response<LoginUser>> responseMutableLiveData;
    private LoginService loginService;

    public LoginRepository() {
        responseMutableLiveData = new MutableLiveData<>();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .header("Authorization", Credentials.basic("xnurhamidanx", "nurhamidan32"))
                        .build();
                return chain.proceed(request);
            }
        }).build();

        loginService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginService.class);
    }

    public MutableLiveData<Response<LoginUser>> getResponseMutableLiveData() {
        return responseMutableLiveData;
    }

    public void create(String username, String password) {
        loginService.create(username, password)
                .enqueue(new Callback<LoginUser>() {
                    @Override
                    public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                        responseMutableLiveData.postValue(response);
                        //System.out.println("println " + response.code());
                    }

                    @Override
                    public void onFailure(Call<LoginUser> call, Throwable t) {
                        responseMutableLiveData.postValue(null);
                        //System.out.println("println " + "gagal");
                    }
                });
    }
}
