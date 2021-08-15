package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.my.nurhamidan.epil.databinding.ActivityAddCandidateBinding;
import id.my.nurhamidan.epil.models.Voting;
import id.my.nurhamidan.epil.viewmodels.CandidatesViewModel;
import retrofit2.Response;

public class AddCandidateActivity extends AppCompatActivity {
    private CandidatesViewModel viewModel;
    private ActivityAddCandidateBinding binding;
    private Voting voting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCandidateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(CandidatesViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Berhasil ditambahkan.", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal Menambahkan.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });

        Intent intent = getIntent();
        voting = (Voting) intent.getSerializableExtra("voting");

        binding.saveCandidateMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.create(binding.candidateNameTextInputEditText.getText().toString(), voting.getId());
            }
        });
    }
}