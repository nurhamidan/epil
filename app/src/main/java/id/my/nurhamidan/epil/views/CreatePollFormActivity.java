package id.my.nurhamidan.epil.views;

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
import id.my.nurhamidan.epil.models.Poll;
import id.my.nurhamidan.epil.models.User;
import id.my.nurhamidan.epil.viewmodels.PollViewModel;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class CreatePollFormActivity extends AppCompatActivity {

    private EditText pollName;
    private Button createButton;
    private PollViewModel pollViewModel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll_form);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        pollName = findViewById(R.id.activity_create_poll_form_poll_name);
        createButton = findViewById(R.id.activity_create_poll_form_button_create);

        pollViewModel = ViewModelProviders.of(this).get(PollViewModel.class);
        pollViewModel.getResponseLiveData().observe(this, new Observer<Response<ResponseBody>>() {
            @Override
            public void onChanged(Response<ResponseBody> responseBodyResponse) {
                if (responseBodyResponse != null) {
                    if (responseBodyResponse.code() == 201) {
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

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = pollName.getEditableText().toString();
                Integer userId = user.getId();
                pollViewModel.create(name, userId);
            }
        });
    }
}