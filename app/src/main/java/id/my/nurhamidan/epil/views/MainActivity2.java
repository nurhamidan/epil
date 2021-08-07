package id.my.nurhamidan.epil.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.my.nurhamidan.epil.R;
import id.my.nurhamidan.epil.views.fragments.LoginFragment;

public class MainActivity2 extends AppCompatActivity {
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        testButton = findViewById(R.id.fragment_login_sign_in_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_navhostfragment, LoginFragment.newInstance("tes", "t"));
                fragmentTransaction.commit();
            }
        });
    }
}