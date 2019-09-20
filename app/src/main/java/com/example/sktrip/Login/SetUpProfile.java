package com.example.sktrip.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.sktrip.R;

public class SetUpProfile extends AppCompatActivity {

    ImageButton getSetGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_profile);

        getSetGo = findViewById(R.id.getSetGo);

        Toolbar tb =  findViewById(R.id.app_toolbar);
        setSupportActionBar(tb) ;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar ab = getSupportActionBar() ;

        getSetGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Splash.class);
                startActivity(intent);
            }
        });
    }
}
