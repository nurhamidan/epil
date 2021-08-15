package id.my.nurhamidan.epil.views;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.my.nurhamidan.epil.R;
import id.my.nurhamidan.epil.databinding.ActivityLoginBinding;
import id.my.nurhamidan.epil.databinding.ActivityNewVotingBinding;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.viewmodels.PollViewModel;
import id.my.nurhamidan.epil.viewmodels.VotingViewModel;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class NewVotingActivity extends AppCompatActivity {
    private ActivityNewVotingBinding binding;
    private VotingViewModel viewModel;
    private User user;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewVotingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        viewModel = ViewModelProviders.of(this).get(VotingViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Berhasil dibuat.", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal dibuat.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.newVotingMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.votingNameTextInputEditText.getText().toString();
                Integer userId = user.getId();
                viewModel.create(name, userId);
            }
        });
    }
}