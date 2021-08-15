package id.my.nurhamidan.epil.repositories;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;

import id.my.nurhamidan.epil.apis.Service;
import id.my.nurhamidan.epil.apis.UserService;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.utils.Constants;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    protected OkHttpClient okHttpClient;
    protected Retrofit retrofit;
    protected MutableLiveData<Response> responseMutableLiveData;
    private static final String BASE_URL = "https://api-epil.nurhamidan.my.id/";
    private static final String AUTH_USERNAME = "xnurhamidanx";
    private static final String AUTH_PASSWORD = "nurhamidan32";

    public Repository() {
        this.okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .header("Authorization", Credentials.basic(AUTH_USERNAME, AUTH_PASSWORD))
                        .build();
                return chain.proceed(request);
            }
        }).build();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(this.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        responseMutableLiveData = new MutableLiveData();
    }

    public MutableLiveData<Response> getResponseMutableLiveData() {
        return responseMutableLiveData;
    }
}
