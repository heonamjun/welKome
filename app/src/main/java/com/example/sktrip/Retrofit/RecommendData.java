package com.example.sktrip.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecommendData {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("addr1")
    @Expose
    private String addr1;
    @SerializedName("firstimage")
    @Expose
    private String firstimage;
    @SerializedName("contentid")
    @Expose
    private int contentid;
    @SerializedName("count(*)")
    @Expose
    private Integer count;
    @SerializedName("mapx")
    @Expose
    private double mapx;
    @SerializedName("mapy")
    @Expose
    private double mapy;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public int getContentid() {
        return contentid;
    }

    public void setContentid(int contentid) {
        this.contentid = contentid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(double mapx) {
        this.mapx = mapx;
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(double mapy) {
        this.mapy = mapy;
    }
}
