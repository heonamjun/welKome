package com.example.sktrip.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class checkData {

    @SerializedName("checkbox")
    @Expose
    private Integer checkbox;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("contentid")
    @Expose
    private Integer contentid;

    @SerializedName("count(*)")
    @Expose
    private Integer count;

    public Integer getCheck() {
        return checkbox;
    }

    public void setCheck(Integer checkbox) {
        this.checkbox = checkbox;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
