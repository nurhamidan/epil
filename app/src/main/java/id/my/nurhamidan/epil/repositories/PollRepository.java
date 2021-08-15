package id.my.nurhamidan.epil.repositories;

import androidx.lifecycle.MutableLiveData;

import id.my.nurhamidan.epil.apis.PollService;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PollRepository {
    private static final String BASE_URL = "https://api-epil.nurhamidan.my.id/";

    private MutableLiveData<Response<ResponseBody>> responseMutableLiveData;
    private PollService pollService;

    public PollRepository() {
        responseMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        pollService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PollService.class);
    }

    public void create(String name, Integer userId) {
        pollService.create(name, userId)
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

    public MutableLiveData<Response<ResponseBody>> getResponseMutableLiveData() {
        return responseMutableLiveData;
    }
}
