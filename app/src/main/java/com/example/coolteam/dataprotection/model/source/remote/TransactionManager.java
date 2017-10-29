package com.example.coolteam.dataprotection.model.source.remote;

import com.example.coolteam.dataprotection.Constants;
import com.example.coolteam.dataprotection.model.Callback;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class TransactionManager{

    public void getTransactions(final Callback callback){
        callback.onLoadStarted();
        Constants.getApi().getTransactions().enqueue(new retrofit2.Callback<List<Transaction>>() {
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
}
