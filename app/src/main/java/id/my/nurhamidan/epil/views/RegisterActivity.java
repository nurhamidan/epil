package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import id.my.nurhamidan.epil.databinding.ActivityLoginBinding;
import id.my.nurhamidan.epil.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}