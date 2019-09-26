package com.example.sktrip.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("sign/register")
    Call<registerData> doRegisterData(@Query("userId") String userId, @Query("userPw") String userPw, @Query("userName") String userName);

    @GET("sign/login")
    Call<List<registerData>> doLoginData(@Query("userId") String userId, @Query("userPw") String userPw, @Query("userName") String userName);



}