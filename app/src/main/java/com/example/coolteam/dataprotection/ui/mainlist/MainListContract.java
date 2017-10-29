package com.example.coolteam.dataprotection.ui.mainlist;

import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

public interface MainListContract {

    interface View{
        void onTransactionsLoaded(List<Transaction> transactions);
        void onTransactionsLoadFailed(String message);
    }

    interface Presenter{
        void onLoadTransactions();
    }

}
