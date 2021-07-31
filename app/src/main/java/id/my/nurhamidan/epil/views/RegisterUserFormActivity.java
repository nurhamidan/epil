package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.my.nurhamidan.epil.R;
import id.my.nurhamidan.epil.viewmodels.UserViewModel;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class RegisterUserFormActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signUpButton;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_form);
        nameEditText = findViewById(R.id.activity_register_user_form_edit_text_name);
        emailEditText = findViewById(R.id.activity_register_user_form_edit_text_email);
        usernameEditText = findViewById(R.id.activity_register_user_form_edit_text_username);
        passwordEditText = findViewById(R.id.activity_register_user_form_edit_text_password);
        signUpButton = findViewById(R.id.activity_register_user_form_button_daftar);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getResponsBodyResponseLiveData().observe(this, new Observer<Response<ResponseBody>>() {
            @Override
            public void onChanged(Response<ResponseBody> responseBodyResponse) {
                if (responseBodyResponse != null) {
                    if (responseBodyResponse.code() == 201) {
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

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = nameEditText.getEditableText().toString();
                String email = emailEditText.getEditableText().toString();
                String username = usernameEditText.getEditableText().toString();
                String password = passwordEditText.getEditableText().toString();
                userViewModel.create(nama, email, username, password);
            }
        });
    }
}