package com.example.coolteam.dataprotection.model.source.remote;

import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface TransactionAPI {

    @GET ("/transaction")
    Call<List<Transaction>> getTransactions();

    @POST("/transaction")
    Call<String> addTransaction(@Body Transaction transaction);

    @DELETE("/transaction")
    Call<String> deleteTransaction(@Field("id") int id);

    @PUT("/transaction")
    Call<String> updateTransaction(@Body Transaction transaction);

    @Multipart
    @POST("http://uploads.im/api?format=txt")
    Call<String> uploadImage(@Part MultipartBody.Part image);

}
