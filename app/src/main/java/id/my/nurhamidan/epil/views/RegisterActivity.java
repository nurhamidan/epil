package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import id.my.nurhamidan.epil.databinding.ActivityLoginBinding;
import id.my.nurhamidan.epil.databinding.ActivityRegisterBinding;
import id.my.nurhamidan.epil.exceptions.PasswordRepeatNotMatchException;
import id.my.nurhamidan.epil.viewmodels.UserViewModel;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Berhasil mendaftar.", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal mendaftar.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.registerMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.nameTextInputEditText.getText().toString();
                String email = binding.emailTextInputEditText.getText().toString();
                String username = binding.usernameTextInputEditText.getText().toString();
                String password = binding.passwordTextInputEditText.getText().toString();
                String passwordRepeat = binding.repeatPasswordTextInputEditText.getText().toString();
                try {
                    viewModel.create(nama, email, username, password, passwordRepeat);
                } catch (PasswordRepeatNotMatchException e) {
                    binding.passwordRepeatTextInputLayout.setError("Kata Sandi Tidak Cocok.");
                }
            }
        });
        binding.repeatPasswordTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.passwordRepeatTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}