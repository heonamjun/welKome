package com.example.sktrip.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sktrip.R;
import com.example.sktrip.Retrofit.APIClient;
import com.example.sktrip.Retrofit.APIInterface;
import com.example.sktrip.Retrofit.registerData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    ImageButton proceed;
    EditText editId, editPw, editConfirmPw, editName;
    String userId, userPw, userConfirmPw, userName;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        proceed = findViewById(R.id.proceed);
        editId = findViewById(R.id.idForm);
        editPw = findViewById(R.id.pwForm);
        editConfirmPw = findViewById(R.id.pwConfirmForm);
        editName = findViewById(R.id.nameForm);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Toolbar tb =  findViewById(R.id.app_toolbar);
        setSupportActionBar(tb) ;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar ab = getSupportActionBar() ;

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userId = editId.getText().toString();
                userName = editName.getText().toString();
                userPw = editPw.getText().toString();
                userConfirmPw = editConfirmPw.getText().toString();

                // 비밀번호 체크 통과
                if(userPw.equals(userConfirmPw)){

                    Call<registerData> call = apiInterface.doRegisterData(userId,userPw,userName);

                    call.enqueue(new Callback<registerData>() {
                        @Override
                        public void onResponse(Call<registerData> call, Response<registerData> response) {

                            Toast.makeText(getApplicationContext(), "ID 생성 완료", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<registerData> call, Throwable t) {

                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext(), "동일한 비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                }



                Intent intent = new Intent(getApplicationContext(), Splash.class);
                startActivity(intent);
            }
        });

    }
}
