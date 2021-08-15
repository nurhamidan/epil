package id.my.nurhamidan.epil.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import id.my.nurhamidan.epil.apis.BookSearchService;
import id.my.nurhamidan.epil.models.VolumesResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";

    private MutableLiveData<VolumesResponse> volumeResponseLiveData;
    private BookSearchService bookSearchService;

    public BookRepository() {
        volumeResponseLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        bookSearchService = new Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchService.class);
    }

    public void searchVolumes(String keyword, String author) {
        bookSearchService.searchVolumes(keyword, author)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {
                            volumeResponseLiveData.postValue(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumeResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumeResponseLiveData;
    }
}
