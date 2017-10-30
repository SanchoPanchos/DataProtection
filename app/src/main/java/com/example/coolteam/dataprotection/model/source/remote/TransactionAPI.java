package com.example.coolteam.dataprotection.model.source.remote;

import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface TransactionAPI {

    @GET ("/transaction")
    Call<List<Transaction>> getTransactions();

    @POST("/transaction")
    Call<String> addTransaction(@Body Transaction transaction);

    @DELETE("/transaction")
    Call<String> deleteTransaction(@Field("id") int id);

    @PUT("/transaction")
    Call<String> updateTransaction(@Body Transaction transaction);

}
