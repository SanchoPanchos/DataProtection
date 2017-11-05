package com.example.coolteam.dataprotection.model.source.remote;

import com.example.coolteam.dataprotection.Constants;
import com.example.coolteam.dataprotection.model.LoadCallback;
import com.example.coolteam.dataprotection.model.ImageUploadResponse;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;

public class TransactionManager{

    private static TransactionAPI api = Constants.getApi();

    public void getTransactions(final LoadCallback loadCallback){
        api.getTransactions().enqueue(new retrofit2.Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                loadCallback.onLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                loadCallback.onNotLoaded(t.getMessage());
            }
        });
    }

    public void deleteTransaction(int id, final LoadCallback loadCallback){
        api.deleteTransaction(id).enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                loadCallback.onLoaded(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                loadCallback.onNotLoaded(t.getMessage());
            }
        });
    }

    public void addTransaction(Transaction transaction, final LoadCallback loadCallback){
        api.addTransaction(transaction).enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                loadCallback.onLoaded(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                loadCallback.onNotLoaded(t.getMessage());
            }
        });
    }

    public void updateTransaction(Transaction transaction, final LoadCallback loadCallback){
        api.updateTransaction(transaction).enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                loadCallback.onLoaded(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                loadCallback.onNotLoaded(t.getMessage());
            }
        });
    }

    public void uploadImage(MultipartBody.Part image, final LoadCallback<ImageUploadResponse> loadCallback){
        api.uploadImage(image).enqueue(new retrofit2.Callback<ImageUploadResponse>() {
            @Override
            public void onResponse(Call<ImageUploadResponse> call, Response<ImageUploadResponse> response) {
                loadCallback.onLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ImageUploadResponse> call, Throwable t) {
                loadCallback.onNotLoaded(t.getMessage());
            }
        });
    }
}
