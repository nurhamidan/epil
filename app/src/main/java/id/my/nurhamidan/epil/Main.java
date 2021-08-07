package id.my.nurhamidan.epil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import id.my.nurhamidan.epil.views.LoginActivity;


public class Main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
