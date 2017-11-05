package com.example.coolteam.dataprotection.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageUploadResponse implements Serializable{

    @SerializedName("data")
    Data data;

    @SerializedName("status_code")
    private int status_code;

    public Data getData() {
        return data;
    }

    public ImageUploadResponse(Data data, int status_code) {
        this.data = data;
        this.status_code = status_code;
    }

    public ImageUploadResponse(){

    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
}
