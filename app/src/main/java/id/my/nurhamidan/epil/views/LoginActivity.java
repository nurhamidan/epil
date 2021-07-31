package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.my.nurhamidan.epil.R;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.viewmodels.UserViewModel;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signinButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        signinButton = findViewById(R.id.sign_in_button);
        signupButton = findViewById(R.id.sign_up_button);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getResponseLiveData().observe(this, new Observer<Response<User>>() {
            @Override
            public void onChanged(Response<User> userResponse) {
                if (userResponse != null) {
                    if (userResponse.code() == 201) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("user", userResponse.body());
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Login gagal.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getEditableText().toString();
                String password = passwordEditText.getEditableText().toString();

                userViewModel.login(username, password);

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterUserFormActivity.class);
                startActivity(intent);
            }
        });

    }

}