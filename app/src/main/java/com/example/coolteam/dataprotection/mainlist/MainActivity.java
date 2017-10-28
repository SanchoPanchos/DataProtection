package com.example.coolteam.dataprotection.mainlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainListContract.View{

    MainListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainListPresenter(this);
        presenter.onLoadTransactions();
    }

    @Override
    public void onTransactionsLoaded(List<Transaction> transactions) {
        Log.i("TAG", transactions.get(0).getLocation());
    }

    @Override
    public void onTransactionsLoadFailed(String message) {

    }
}
