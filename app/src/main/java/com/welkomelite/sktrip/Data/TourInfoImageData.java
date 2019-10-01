package com.welkomelite.sktrip.Data;

public class TourInfoImageData {
    private String originimgurl;
    private String serialnum;
    private String smallimageurl;

    public TourInfoImageData(String originimgurl, String serialnum, String smallimageurl){
        this.originimgurl = originimgurl;
        this.serialnum = serialnum;
        this.smallimageurl = smallimageurl;
    }

    public String getOriginimgurl() {
        return originimgurl;
    }

    public void setOriginimgurl(String originimgurl) {
        this.originimgurl = originimgurl;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getSmallimageurl() {
        return smallimageurl;
    }

    public void setSmallimageurl(String smallimageurl) {
        this.smallimageurl = smallimageurl;
    }
}
