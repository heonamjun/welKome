package com.example.sktrip.TourApi;

import com.example.sktrip.TourApi.Model.DataRES;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TourApiService {

    @GET("areaBasedList?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&contentTypeId=15&areaCode=4&sigunguCode=4&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getareaBasedList();

//            [지역코드 조회]
//            [서비스 분류코드조회]
//            [지역기반 관광정보조회]
//            [위치기반 관광정보조회]
//            [키워드 검색조회]
//            [행사정보 조회]
//            [숙박정보 조회]
//            [공통정보 조회]
//            [소개정보 조회]
//            [반복정보 조회]
//            [이미지정보 조회]


    //지역 코드 조회
    //평균적으로  ok
    @GET("areaCode?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getareaCode(@Query("areaCode") int areaCode,
                              @Query("numOfRows") int numOfRows,
                              @Query("pageNo") int pageNo);

    //서비스 분류 조회
    //contentTypeId 분류 - 관광지 12 , 문화시설 14 , 행사/공연/축제 15 , 여행코스 25 , 레포츠 28 , 숙박 32 , 쇼핑 38 , 음식점 39
    //*************주의************** contentTypeId , cat1 요청필수
    @GET("categoryCode?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getcategoryCode(@Query("contentTypeId") int contentTypeId,
                                  @Query("cat1") String cat1,
                                  @Query("numOfRows") int numOfRows,
                                  @Query("pageNo") int pageNo);

    @GET("categoryCode?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getcategoryCode(@Query("contentTypeId") int contentTypeId,
                                  @Query("cat1") String cat1,
                                  @Query("cat2") String cat2,
                                  @Query("numOfRows") int numOfRows,
                                  @Query("pageNo") int pageNo);

    @GET("categoryCode?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getcategoryCode(@Query("contentTypeId") int contentTypeId,
                                  @Query("cat1") String cat1,
                                  @Query("cat2") String cat2,
                                  @Query("cat3") String cat3,
                                  @Query("numOfRows") int numOfRows,
                                  @Query("pageNo") int pageNo);

    //지역 기반 조회
    //정렬(arrange) - (A=제목순, B=조회순, 수정일순, D=생성일순) 대표이미지가 반드시 있는 정렬 , (O=제목순, P=조회순, Q=수정일순, R=생성일순)
    //목록 구분(listYN)(Y=목록, N=개수)

    @GET("areaBasedList?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getareaBasedList(@Query("listYN") String listYN,
                                   @Query("arrange") String arrange,
                                   @Query("contentTypeId") int contentTypeId,
                                   @Query("areaCode") int areaCode,
                                   @Query("sigunguCode") int sigunguCode,
                                   @Query("cat1") String cat1,
                                   @Query("cat2") String cat2,
                                   @Query("cat3") String cat3,
                                   @Query("numOfRows") int numOfRows,
                                   @Query("pageNo") int pageNo);

    @GET("areaBasedList?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json&listYN=Y&arrange=B")
    Call<DataRES> getareaBasedList(@Query("listYN") String listYN,
                                   @Query("arrange") String arrange,
                                   @Query("contentTypeId") int contentTypeId,
                                   @Query("areaCode") int areaCode,
                                   @Query("numOfRows") int numOfRows,
                                   @Query("pageNo") int pageNo);

    @GET("areaBasedList?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json&listYN=Y&arrange=B")
    Call<DataRES> getareaBasedListt(@Query("contentTypeId") int contentTypeId,
                                    @Query("areaCode") int areaCode,
                                    @Query("numOfRows") int numOfRows,
                                    @Query("pageNo") int pageNo);

    //위치 기반 관광정보 조회
    //GPS X좌표(WGS84 경도 좌표),GPS Y좌표(WGS84 위도 좌표)
    @GET("locationBasedList?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getlocationBasedList(@Query("listYN") String listYN,
                                       @Query("arrange") String arrange,
                                       @Query("contentTypeId") int contentTypeId,
                                       @Query("mapX") float mapX,
                                       @Query("mapY") float mapY,
                                       @Query("radius") int radius,
                                       @Query("numOfRows") int numOfRows,
                                       @Query("pageNo") int pageNo);

    //키워드 검색 조회 ok
    @GET("searchKeyword?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getsearchKeyword(@Query("listYN") String listYN,
                                   @Query("arrange") String arrange,
                                   @Query("contentTypeId") int contentTypeId,
                                   @Query("keyword") String keyword,
                                   @Query("numOfRows") int numOfRows,
                                   @Query("pageNo") int pageNo);

    //행사 정보 조회
    @GET("searchFestival?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getsearchFestival(@Query("listYN") String listYN,
                                    @Query("arrange") String arrange,
                                    @Query("contentTypeId") int contentTypeId,
                                    @Query("sigunguCode") int sigunguCode,
                                    @Query("eventStartDate") int eventStartDate,
                                    @Query("eventEndDate") int eventEndDate,
                                    @Query("numOfRows") int numOfRows,
                                    @Query("pageNo") int pageNo);

    //숙박 정보 조회 ok
    @GET("searchStay?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getsearchStay(@Query("listYN") String listYN,
                                @Query("arrange") String arrange,
                                @Query("areaCode") int areaCode,
                                @Query("numOfRows") int numOfRows,
                                @Query("pageNo") int pageNo);

    //공통정보 조회 ok
    @GET("detailCommon?serviceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&numOfRows=10&pageNo=1&MobileOS=AND&MobileApp=AppTest&defaultYN=Y&firstImageYN=Y&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=N&overviewYN=Y&_type=json")
    Call<DataRES> getdetailCommon(@Query("contentId") int contentId);

    //소개 정보 조회
    @GET("detailIntro?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getdetailIntro(@Query("contentId") int contentId,
                                 @Query("numOfRows") int numOfRows,
                                 @Query("pageNo") int pageNo);

    @GET("detailIntro?serviceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&contentId=1118680&contentTypeId=15&_type=json")
    Call<DataRES> getdetailIntro();


    //반복 정보 조회
    @GET("detailInfo?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getdetailInfo(@Query("contentId") int contentId,
                                @Query("contentTypeId") int contentTypeId,
                                @Query("numOfRows") int numOfRows,
                                @Query("pageNo") int pageNo);

    //이미지 정보 조회 ok
    @GET("detailImage?ServiceKey=Wbxwtl8WwgmVKhpp9KzLIfEFqOJnJAOHysiyOKV9uVKu6wmZlE3Tf4h94V6%2Fg48FQmWRN2uVR2RLziOZi5vnnA%3D%3D&MobileOS=AND&MobileApp=AppTest&_type=json")
    Call<DataRES> getdetailImage(@Query("contentId") int contentId,
                                 @Query("imageYN") String imageYN,
                                 @Query("subImageYN") String subImageYN,
                                 @Query("numOfRows") int numOfRows,
                                 @Query("pageNo") int pageNo);

}
