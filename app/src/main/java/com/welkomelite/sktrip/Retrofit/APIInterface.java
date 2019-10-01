package com.welkomelite.sktrip.Retrofit;

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
    Call<List<ratingData>> doRatingDataInsert(@Query("userId") String userId, @Query("rating") int rating, @Query("contentid") int contentid);

    @GET("rating/load")
    Call<List<ratingData>> RationDataLoad(@Query("userId") String userId,  @Query("contentid") int contentid);

    @GET("rating/count")
    Call<List<ratingData>> RatingDataCount(@Query("userId") String userId);

    @GET("mypage/gradelist")
    Call<List<MyPageData>> MyPageGradeList(@Query("userId") String userId);

    @GET("likepage/likelist")
    Call<List<LikeData>> LikePageList(@Query("userId") String userId);

    @GET("tourcheck/insert")
    Call<List<checkData>> doCheckDataInsert(@Query("userId") String userId, @Query("checkbox") int checkbox, @Query("contentid") int contentid);

    @GET("tourcheck/load")
    Call<List<checkData>> doCheckDataLoad(@Query("userId") String userId, @Query("contentid") int contentid);

    @GET("recommend/read")
    Call<List<RecommendData>> ReadRecommend();
}
