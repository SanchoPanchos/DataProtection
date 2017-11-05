package com.example.coolteam.dataprotection.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Data implements Serializable{

    @SerializedName("img_url")
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public Data(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Data(){

    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
