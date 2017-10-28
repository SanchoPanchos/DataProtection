package com.example.coolteam.dataprotection.mainlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.fragments.TransactionsListFragment;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        if (findViewById(R.id.transactions_fragment_container) != null) {
//            if (savedInstanceState != null) {
//                return;
//            }

            TransactionsListFragment transactionsListFragment = new TransactionsListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.transactions_fragment_container, transactionsListFragment).commit();
        }
    }
}
