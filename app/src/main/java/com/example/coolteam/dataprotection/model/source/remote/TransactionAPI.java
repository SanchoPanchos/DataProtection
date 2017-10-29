package com.example.coolteam.dataprotection.model.source.remote;

import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TransactionAPI {

    @GET ("/transaction")
    Call<List<Transaction>> getTransactions();

    @POST("/transaction")
    Call<String> addTransaction(@Body Transaction transaction);

}
