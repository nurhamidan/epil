package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import id.my.nurhamidan.epil.databinding.ActivityEditProfileBinding;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.utils.preferences.LoggedUserPreferenceManager;
import id.my.nurhamidan.epil.viewmodels.UserViewModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private ActivityEditProfileBinding binding;
    private User user;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Berhasil diubah.", Toast.LENGTH_LONG).show();
                        finish();
                    } else if (response.code() == 202) {
                        Toast.makeText(getApplicationContext(), "Tidak ada perubahan.", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal Diubah. " + Integer.toString(response.code()), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                }
            }
        });

        user = new LoggedUserPreferenceManager(this).getUser();
        binding.nameTextInputEditText.setText(user.getName());
        binding.emailTextInputEditText.setText(user.getEmail());

        binding.saveChangesMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.nameTextInputEditText.getText().toString();
                String email = binding.emailTextInputEditText.getText().toString();
                viewModel.update(EditProfileActivity.this.user.getId(), name, email);
            }
        });
    }
}