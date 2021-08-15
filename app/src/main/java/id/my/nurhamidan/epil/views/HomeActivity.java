package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.my.nurhamidan.epil.databinding.ActivityHomeBinding;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.utils.preferences.LoggedUserPreferenceManager;
import id.my.nurhamidan.epil.viewmodels.UserViewModel;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private UserViewModel viewModel;
    private ActivityHomeBinding binding;
    private User loggedUser;

    @Override
    protected void onResume() {
        super.onResume();
        loggedUser = new LoggedUserPreferenceManager(this).getUser();
        binding.nameTextView.setText(loggedUser.getName());
        binding.emailTextView.setText(loggedUser.getEmail());
        binding.usernameTextView.setText(loggedUser.getUsername());
        viewModel.login(loggedUser.getUsername(), loggedUser.getPassword());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null) {
                    if (response.code() == 200) {
                        User user = (User) response.body();
                        binding.nameTextView.setText(user.getName());
                        binding.emailTextView.setText(user.getEmail());
                        binding.usernameTextView.setText(user.getUsername());
                        new LoggedUserPreferenceManager(getApplicationContext(), user);
                        System.out.println("test");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.createVotingMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewVotingActivity.class);
                intent.putExtra("user", loggedUser); //ToDo jangan put extra
                startActivity(intent);
            }
        });

        binding.votingsMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VotingsActivity.class);
                intent.putExtra("user", loggedUser); //ToDo jangan put extra
                startActivity(intent);
            }
        });

        binding.logoutMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoggedUserPreferenceManager preferenceManager = new LoggedUserPreferenceManager(getApplicationContext());
                preferenceManager.setOutUser();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.editProfileMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
            }
        });
    }
}