package id.my.nurhamidan.epil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.utils.preferences.LoggedUserPreferenceManager;
import id.my.nurhamidan.epil.viewmodels.UserViewModel;
import id.my.nurhamidan.epil.views.HomeActivity;
import id.my.nurhamidan.epil.views.LoginActivity;
import retrofit2.Response;


public class Main extends AppCompatActivity {
    private UserViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null) {
                    if (response.code() == 200) {
                        User user = (User) response.body();
                        LoggedUserPreferenceManager loggedUserPreferenceManager = new LoggedUserPreferenceManager(getApplicationContext(), user);
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak terhubung ke jaringan.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        User user = new LoggedUserPreferenceManager(this).getUser();
        viewModel.login(user.getUsername(), user.getPassword());
    }
}
