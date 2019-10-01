package com.welkomelite.sktrip.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ratingData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("contentid")
    @Expose
    private Integer contentid;
    @SerializedName("count(*)")
    @Expose
    private Integer count;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

}
