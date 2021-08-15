package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.my.nurhamidan.epil.repositories.Repository;
import retrofit2.Response;

public abstract class ViewModel extends AndroidViewModel {
    protected LiveData<Response> responseLiveData;
    public ViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Response> getResponseLiveData() {
        return responseLiveData;
    }

}
