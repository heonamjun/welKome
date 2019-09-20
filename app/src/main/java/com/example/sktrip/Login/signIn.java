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

import com.example.sktrip.MainActivity;
import com.example.sktrip.R;
import com.example.sktrip.Retrofit.APIClient;
import com.example.sktrip.Retrofit.APIInterface;
import com.example.sktrip.Retrofit.registerData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signIn extends AppCompatActivity {

    ImageButton goToFeed;
    EditText editID, editPW;
    String userID, userPW="asdfdsagshfhfgfgf", ID,PASSWORD="asdfsdfsdgsdafsdaf", Name;
    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar tb =  findViewById(R.id.app_toolbar);
        setSupportActionBar(tb) ;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar ab = getSupportActionBar() ;

        goToFeed = findViewById(R.id.goToFeed);
        editID = findViewById(R.id.idForm);
        editPW = findViewById(R.id.pwForm);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        goToFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userID = editID.getText().toString();
                userPW = editPW.getText().toString();

                Call<List<registerData>> call = apiInterface.doLoginData(userID,userPW,"zz");

                call.enqueue(new Callback<List<registerData>>() {
                    @Override
                    public void onResponse(Call<List<registerData>> call, Response<List<registerData>> response) {


                        for(registerData data : response.body()){
                            ID = data.getId();
                            PASSWORD = data.getPassword();
                            Name = data.getName();
                        }



                        if(PASSWORD.equals(userPW)){ // 널 예외처리 귀찮아서 그냥 글자 넣겟음

                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(), "비밀번호가 맞지 않습니다.", Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<List<registerData>> call, Throwable t) {
                        Log.d("TAG", t.toString());
                        call.cancel();
                    }
                });



            }
        });

    }
}
