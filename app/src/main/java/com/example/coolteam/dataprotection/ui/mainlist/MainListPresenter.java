package com.example.coolteam.dataprotection.ui.mainlist;


import com.example.coolteam.dataprotection.model.Callback;
import com.example.coolteam.dataprotection.model.Transaction;
import com.example.coolteam.dataprotection.model.source.remote.TransactionManager;

import java.util.List;

public class MainListPresenter implements MainListContract.Presenter{

    private TransactionManager transactionManager = new TransactionManager();
    private MainListContract.View view;

    public MainListPresenter(MainListContract.View view) {
        this.view = view;
    }

    @Override
    public void onLoadTransactions() {
        transactionManager.getTransactions(new Callback<List<Transaction>>() {
            @Override
            public void onLoadStarted() {

            }

            @Override
            public void onLoaded(List<Transaction> data) {
                view.onTransactionsLoaded(data);
            }

            @Override
            public void onNotLoaded(String error) {
                view.onTransactionsLoadFailed(error);
            }
        });
    }


}
