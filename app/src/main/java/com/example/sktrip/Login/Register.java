package com.example.sktrip.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sktrip.R;
import com.example.sktrip.Retrofit.APIClient;
import com.example.sktrip.Retrofit.APIInterface;
import com.example.sktrip.Retrofit.registerData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    ImageButton proceed, idCheck;
    EditText editId, editPw, editConfirmPw, editName;
    String userId, userPw, userConfirmPw, userName;
    String count, frag="no";

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        proceed = findViewById(R.id.proceed);
        idCheck = findViewById(R.id.idCheck);
        editId = findViewById(R.id.idForm);
        editPw = findViewById(R.id.pwForm);
        editConfirmPw = findViewById(R.id.pwConfirmForm);
        editName = findViewById(R.id.nameForm);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Toolbar tb =  findViewById(R.id.app_toolbar);
        setSupportActionBar(tb) ;
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar ab = getSupportActionBar() ;

        idCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userId = editId.getText().toString();

                Call<List<registerData>> call = apiInterface.doIdCheck(userId);

                call.enqueue(new Callback<List<registerData>>() {
                    @Override
                    public void onResponse(Call<List<registerData>> call, Response<List<registerData>> response) {

                        count="";
                        frag="no";

                        for(registerData data : response.body()){
                            count = String.valueOf(data.getCount());
                            Log.d("tag",count);
                        }

                        if(count.equals("1")){
                            Toast.makeText(getApplicationContext(), "중복된 ID 입니다.", Toast.LENGTH_LONG).show();
                            frag="no";
                        }else{
                            Toast.makeText(getApplicationContext(), "사용 가능한 ID 입니다.", Toast.LENGTH_LONG).show();
                            frag="ok";
                        }

                    }

                    @Override
                    public void onFailure(Call<List<registerData>> call, Throwable t) {

                    }
                });

            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userId = editId.getText().toString();
                userName = editName.getText().toString();
                userPw = editPw.getText().toString();
                userConfirmPw = editConfirmPw.getText().toString();

                // 비밀번호 체크 통과
                if(userPw.equals(userConfirmPw) && frag.equals("ok")){

                    Call<registerData> call = apiInterface.doRegisterData(userId,userPw,userName);

                    call.enqueue(new Callback<registerData>() {
                        @Override
                        public void onResponse(Call<registerData> call, Response<registerData> response) {

                            Toast.makeText(getApplicationContext(), "ID 생성 완료", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), Splash.class);
                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<registerData> call, Throwable t) {

                        }
                    });

                }else if(frag == "no"){
                    Toast.makeText(getApplicationContext(), "ID를 확인해주세요", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "올바른 비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}
