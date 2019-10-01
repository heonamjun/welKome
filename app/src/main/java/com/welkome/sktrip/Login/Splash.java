package com.welkome.sktrip.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.welkome.sktrip.R;

public class Splash extends AppCompatActivity {

    ImageButton signInButton;
    ImageButton createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        signInButton = findViewById(R.id.signIn);
        createButton = findViewById(R.id.createButton);

        Toolbar tb =  findViewById(R.id.app_toolbar);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signIn.class);
                startActivity(intent);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

    }

}
