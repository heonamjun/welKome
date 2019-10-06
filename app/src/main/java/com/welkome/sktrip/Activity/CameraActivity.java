package com.welkome.sktrip.Activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.welkome.sktrip.Camera.Camera2BasicFragment;
import com.welkome.sktrip.R;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera2);
        Camera2BasicFragment camera2BasicFragment = new Camera2BasicFragment();


        if (null == savedInstanceState) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.camera_preview, camera2BasicFragment)
                    .commit();
        }
    }
}
