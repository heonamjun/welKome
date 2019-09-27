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

    @GET("sign/idcheck")
    Call<List<registerData>> doIdCheck(@Query("userId") String userId);

    @GET("rating/insert")
    Call<List<ratingData>> doRatingDataInsert(@Query("id") String id, @Query("rating") int rating, @Query("contentid") int contentid);

    @GET("rating/load2")
    Call<List<ratingData>> doRatingDataLoad2(@Query("id") String id, @Query("contentid") int contentid);

    @GET("rating/load")
    Call<List<ratingData>> RationDataLoad(@Query("id") String id,  @Query("contentid") int contentid);

    @GET("rating/count")
    Call<List<ratingData>> RatingDataCount(@Query("id") String id,  @Query("contentid") int contentid);
}

