package id.my.nurhamidan.epil.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.my.nurhamidan.epil.models.VolumesResponse;
import id.my.nurhamidan.epil.repositories.BookRepository;

public class BookSearchViewModel extends AndroidViewModel {
    private BookRepository bookRepository;
    private LiveData<VolumesResponse> volumeResponseLiveData;
    public BookSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        bookRepository = new BookRepository();
        volumeResponseLiveData = bookRepository.getVolumesResponseLiveData();
    }

    public void searchVolumes(String keyword, String author) {
        bookRepository.searchVolumes(keyword, author);
    }


    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumeResponseLiveData;
    }
}
