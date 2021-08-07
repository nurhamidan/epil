package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.my.nurhamidan.epil.R;
import id.my.nurhamidan.epil.models.LoginUser;
import id.my.nurhamidan.epil.models.User;

public class HomeActivity extends AppCompatActivity {
    private LoginUser user;
    private TextView name;
    private TextView email;
    private TextView username;
    private Button createPollButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        user = (LoginUser) intent.getSerializableExtra("user");

        name = findViewById(R.id.activity_home_text_view_name);
        email = findViewById(R.id.activity_home_text_view_email);
        username = findViewById(R.id.activity_home_text_view_username);

        name.setText(user.getName());
        email.setText(user.getEmail());
        username.setText(user.getUsername());

        createPollButton = findViewById(R.id.activity_home_button_create_poll);
        createPollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreatePollFormActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}