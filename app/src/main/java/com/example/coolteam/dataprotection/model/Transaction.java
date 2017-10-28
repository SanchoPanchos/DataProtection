package com.example.coolteam.dataprotection.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("date")
    @Expose
    private long date;

    @SerializedName("requester")
    @Expose
    private String requester;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("transactionCode")
    @Expose
    private String transactionCode;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("requesterLogo")
    @Expose
    private String requesterLogo;

    public Transaction(int id, long date, String requester, String location, String status, String transactionCode, String description, String requesterLogo) {
        this.id = id;
        this.date = date;
        this.requester = requester;
        this.location = location;
        this.status = status;
        this.transactionCode = transactionCode;
        this.description = description;
        this.requesterLogo = requesterLogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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
