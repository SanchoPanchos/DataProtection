package com.example.coolteam.dataprotection.model.source.remote;

import com.example.coolteam.dataprotection.Constants;
import com.example.coolteam.dataprotection.model.Callback;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class TransactionManager{

    public static TransactionAPI api = Constants.getApi();

    public void getTransactions(final Callback callback){
        callback.onLoadStarted();
        api.getTransactions().enqueue(new retrofit2.Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                callback.onLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                callback.onNotLoaded(t.getMessage());
            }
        });
    }

    public void deleteTransaction(int id, final Callback callback){
        callback.onLoadStarted();
        api.deleteTransaction(id).enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onLoaded(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onNotLoaded(t.getMessage());
            }
        });
    }

    public void addTransaction(Transaction transaction, final Callback callback){
        callback.onLoadStarted();
        api.addTransaction(transaction).enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onLoaded(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onNotLoaded(t.getMessage());
            }
        });
    }

    public void updateTransaction(Transaction transaction, final Callback callback){
        callback.onLoadStarted();
        api.updateTransaction(transaction).enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onLoaded(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onNotLoaded(t.getMessage());
            }
        });
    }
}
