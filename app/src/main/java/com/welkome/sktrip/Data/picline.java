package com.welkome.sktrip.Data;

import java.io.Serializable;

public class picline extends RecyclerItem implements Serializable {
    public String gps;
    public String title;
    //public int img;

    public String picUrl;
    public String lineUrl;
    public picline() { //파이어에베이스와 연동하려면 매개변수없는걸로 필요?.ㄵ

    }


    public picline(String gps, String title, String picUrl, String lineUrl/*,int img*/) {
        this.gps = gps;
        this.title = title;
        this.picUrl = picUrl;

    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getLineUrl(){return lineUrl;}
    public void setLineUrl(String lineUrl){this.lineUrl = lineUrl;}
}
