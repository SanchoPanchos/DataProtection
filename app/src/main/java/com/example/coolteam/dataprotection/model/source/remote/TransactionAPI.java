package com.example.coolteam.dataprotection.model.source.remote;

import com.example.coolteam.dataprotection.model.ImageUploadResponse;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface TransactionAPI {

    @GET ("/transaction")
    Call<List<Transaction>> getTransactions();

    @POST("/transaction")
    Call<String> addTransaction(@Body Transaction transaction);

    @DELETE("/transaction")
    Call<String> deleteTransaction(@Query("id") int id);

    @PUT("/transaction")
    Call<String> updateTransaction(@Body Transaction transaction);

    @Multipart
    @POST("http://uploads.im/api")
    Call<ImageUploadResponse> uploadImage(@Part MultipartBody.Part image);

}
