package com.example.coolteam.dataprotection.ui.mainlist;

import android.app.Activity;

import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

public interface MainListContract {

    interface View{
        void onTransactionsLoaded(List<Transaction> transactions);
        void onTransactionsLoadFailed(String message);
        void onTransactionDeleted();
        void onTransactionNotDeleted(String error);
        Activity getActivity();
        void showDialog();
        void hideDialog();
    }

    interface Presenter{
        void onLoadTransactions();
        void onDeleteTransaction(int id);
        void onTransactionDeleted();
        void onTransactionNotDeleted(String error);
    }

}
