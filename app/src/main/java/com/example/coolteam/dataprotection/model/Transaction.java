package com.example.coolteam.dataprotection.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Random;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Transaction extends RealmObject implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private int id;

//    private Date date;
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    @SerializedName("date")
    private long dateLong;

    @SerializedName("requester")
    private String requester;

    @SerializedName("location")
    private String location;

    @SerializedName("status")
    private String status;

    @SerializedName("transactionCode")
    private String transactionCode;

    @SerializedName("description")
    private String description;

    @SerializedName("requesterLogo")
    private String requesterLogo;

    public Transaction(long dateLong, String requester, String location, String status, String transactionCode, String description, String requesterLogo) {
        this.id = new Random().nextInt(1000000000);
        this.dateLong = dateLong;
        //this.date = new Date(dateLong);
        this.requester = requester;
        this.location = location;
        this.status = status;
        this.transactionCode = transactionCode;
        this.description = description;
        this.requesterLogo = requesterLogo;
    }

    public Transaction(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDateLong() {
        return dateLong;
    }

    public void setDateLong(long dateLong) {
        this.dateLong = dateLong;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequesterLogo() {
        return requesterLogo;
    }

    public void setRequesterLogo(String requesterLogo) {
        this.requesterLogo = requesterLogo;
    }
}
