package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import id.my.nurhamidan.epil.databinding.ActivityLoginBinding;
import id.my.nurhamidan.epil.models.LoginUser;
import id.my.nurhamidan.epil.viewmodels.LoginViewModel;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getResponseLiveData().observe(this, new Observer<Response<LoginUser>>() {
            @Override
            public void onChanged(Response<LoginUser> loginUserResponse) {
                if (loginUserResponse != null) {
                    if (loginUserResponse.code() == 200) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("user", loginUserResponse.body());
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Login gagal. " + loginUserResponse.code(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.textUsername.getText().toString();
                String password = binding.textPassword.getText().toString();

                loginViewModel.create(username, password);

            }
        });

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterUserFormActivity.class);
                startActivity(intent);
            }
        });

    }

}