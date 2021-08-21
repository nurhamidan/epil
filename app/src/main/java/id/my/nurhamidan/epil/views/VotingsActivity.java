package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import id.my.nurhamidan.epil.databinding.ActivityLoginBinding;
import id.my.nurhamidan.epil.databinding.ActivityVotingsBinding;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.models.Voting;
import id.my.nurhamidan.epil.utils.recyclerview.adapters.VotingsAdapter;
import id.my.nurhamidan.epil.viewmodels.UserViewModel;
import id.my.nurhamidan.epil.viewmodels.VotingViewModel;
import retrofit2.Response;

public class VotingsActivity extends AppCompatActivity {
    private ActivityVotingsBinding binding;
    private VotingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVotingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(VotingViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null) {
                    if (response.code() == 200) {
                        binding.votingsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        binding.votingsRecyclerView.setAdapter(new VotingsAdapter(VotingsActivity.this, (ArrayList<Voting>) response.body()));
                    } else if (response.code() == 204) {
                        Toast.makeText(getApplicationContext(), "Berhasil Menghapus.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal Mendapatkan Data " + response.code(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });
        viewModel.getVotings();
    }
}