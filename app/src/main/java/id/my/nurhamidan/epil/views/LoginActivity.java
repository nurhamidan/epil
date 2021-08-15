package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;
import id.my.nurhamidan.epil.databinding.ActivityLoginBinding;
import id.my.nurhamidan.epil.models.LoginUser;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.utils.preferences.LoggedUserPreferenceManager;
import id.my.nurhamidan.epil.viewmodels.LoginViewModel;
import id.my.nurhamidan.epil.viewmodels.UserViewModel;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<Response>() {
                    @Override
                    public void onChanged(Response response) {
                        if (response != null) {
                            if (response.code() == 200) {
                                User user = (User) response.body();
                                new LoggedUserPreferenceManager(getApplicationContext(), user);
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Login gagal. " + response.code(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        binding.loginMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.usernameTextInputEditText.getText().toString();
                String password = binding.passwordTextInputEditText.getText().toString();

                viewModel.login(username, password);

            }
        });

        binding.registerMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}