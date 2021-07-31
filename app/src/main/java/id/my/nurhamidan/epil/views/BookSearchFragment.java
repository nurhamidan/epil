package id.my.nurhamidan.epil.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import id.my.nurhamidan.epil.R;
import id.my.nurhamidan.epil.adapters.BookSearchResultsAdapter;
import id.my.nurhamidan.epil.models.VolumesResponse;
import id.my.nurhamidan.epil.viewmodels.BookSearchViewModel;

public class BookSearchFragment extends Fragment {
    private BookSearchViewModel viewModel;
    private BookSearchResultsAdapter adapter;

    private TextInputEditText keywordEditText, authorEditText;
    private Button searchButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new BookSearchResultsAdapter();

        viewModel = ViewModelProviders.of(this).get(BookSearchViewModel.class);
        viewModel.init();
        viewModel.getVolumesResponseLiveData().observe(this, new Observer<VolumesResponse>() {
            @Override
            public void onChanged(VolumesResponse volumesResponse) {
                if (volumesResponse != null) {
                    if (volumesResponse.getTotalItems() != 0) {
                        adapter.setResults(volumesResponse.getItems());
                    } else {
                        Log.d("pesan", "tidak ditemukan");
                    }
                }

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booksearch, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_booksearch_searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        keywordEditText = view.findViewById(R.id.fragment_booksearch_keyword);
        authorEditText = view.findViewById(R.id.fragment_booksearch_author);
        searchButton = view.findViewById(R.id.fragment_booksearch_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = keywordEditText.getEditableText().toString();
                String author = authorEditText.getEditableText().toString();

                viewModel.searchVolumes(keyword, author);
            }
        });

        return view;
    }
}
