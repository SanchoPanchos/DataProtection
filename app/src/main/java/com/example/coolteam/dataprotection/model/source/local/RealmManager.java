package com.example.coolteam.dataprotection.model.source.local;

import android.support.annotation.NonNull;

import com.example.coolteam.dataprotection.model.LoadCallback;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmManager {

    private Realm realm = Realm.getDefaultInstance();

    public void addTransaction(final Transaction transaction, final LoadCallback callBack){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                Transaction transaction1 = realm.copyToRealm(transaction);
                callBack.onLoaded(null);
            }
        });
    }

    public void loadTransactions(final LoadCallback<List<Transaction>> loadCallback){
        RealmResults<Transaction> transactions = realm.where(Transaction.class).findAll();
        List<Transaction> trueTransactions = new ArrayList<>();
        trueTransactions.addAll(transactions);
        loadCallback.onLoaded(trueTransactions);
    }
}
