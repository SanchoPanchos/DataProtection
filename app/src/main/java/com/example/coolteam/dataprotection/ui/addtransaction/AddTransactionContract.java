package com.example.coolteam.dataprotection.ui.addtransaction;


import android.app.Activity;
import android.graphics.Bitmap;

import com.example.coolteam.dataprotection.model.Transaction;

public interface AddTransactionContract {

    interface View{
        void onTransactionAdded();
        void onTransactionNotAdded(String error);
        void showDialog();
        void hideDialog();
        Activity getActivity();
    }

    interface Presenter{
        void onAddTransaction(Transaction transaction, Bitmap image);
    }
}
