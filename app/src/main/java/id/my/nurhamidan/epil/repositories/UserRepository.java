package id.my.nurhamidan.epil.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import id.my.nurhamidan.epil.apis.LoginService;
import id.my.nurhamidan.epil.apis.UserService;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.models.VolumesResponse;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

public class UserRepository {
    private static final String BASE_URL = "https://api-epil.nurhamidan.my.id/";

    private MutableLiveData<Response<User>> responseMutableLiveData;
    private MutableLiveData<Response<ResponseBody>> responseBodyResponseMutableLiveData;
    private UserService userService;

    public UserRepository() {
        responseMutableLiveData = new MutableLiveData<>();
        responseBodyResponseMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        userService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);
    }

    public void create(String nama, String email, String username, String password) {
        userService.create(nama, email, username, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        responseBodyResponseMutableLiveData.postValue(response);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        responseBodyResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public MutableLiveData<Response<User>> getResponseMutableLiveData() {
        return responseMutableLiveData;
    }

    public MutableLiveData<Response<ResponseBody>> getResponseBodyResponseMutableLiveData() {
        return responseBodyResponseMutableLiveData;
    }
}
