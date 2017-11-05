package com.example.coolteam.dataprotection.ui.mainlist;

import com.example.coolteam.dataprotection.model.LoadCallback;
import com.example.coolteam.dataprotection.model.Transaction;
import com.example.coolteam.dataprotection.model.source.local.RealmManager;
import com.example.coolteam.dataprotection.model.source.remote.TransactionManager;
import com.example.coolteam.dataprotection.util.NetworkUtil;

import java.util.List;

public class MainListPresenter implements MainListContract.Presenter{

    private TransactionManager transactionManager;
    private RealmManager realmManager;
    private MainListContract.View view;

    public MainListPresenter(MainListContract.View view) {
        this.view = view;
        this.transactionManager = new TransactionManager();
        this.realmManager = new RealmManager();
    }

    @Override
    public void onLoadTransactions() {
        if(NetworkUtil.isNetworkConnected(view.getActivity())) {
            view.showDialog();
            transactionManager.getTransactions(new LoadCallback<List<Transaction>>() {
                @Override
                public void onLoaded(List<Transaction> data) {
                    view.hideDialog();
                    view.onTransactionsLoaded(data);
                }

                @Override
                public void onNotLoaded(String error) {
                    view.hideDialog();
                    view.onTransactionsLoadFailed(error);
                }
            });
        }
        else {
            view.showDialog();
            realmManager.loadTransactions(new LoadCallback<List<Transaction>>() {
                @Override
                public void onLoaded(List<Transaction> data) {
                    view.hideDialog();
                    view.onTransactionsLoaded(data);
                }

                @Override
                public void onNotLoaded(String error) {
                    view.hideDialog();
                    view.onTransactionsLoadFailed(error);
                }
            });
        }
    }

    @Override
    public void onDeleteTransaction(int id) {
        transactionManager.deleteTransaction(id, new LoadCallback() {
            @Override
            public void onLoaded(Object data) {

            }

            @Override
            public void onNotLoaded(String error) {

            }
        });
    }

    @Override
    public void onTransactionDeleted() {

    }

    @Override
    public void onTransactionNotDeleted(String error) {

    }


}
