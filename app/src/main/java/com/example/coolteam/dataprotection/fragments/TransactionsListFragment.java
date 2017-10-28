package com.example.coolteam.dataprotection.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.adapters.TransactionAdapter;
import com.example.coolteam.dataprotection.mainlist.MainListContract;
import com.example.coolteam.dataprotection.mainlist.MainListPresenter;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

public class TransactionsListFragment extends Fragment implements MainListContract.View {

    MainListContract.Presenter presenter;

    private Context context;
    private View view;

    TransactionAdapter transactionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transactions_list, container, false);

        context = this.getContext();

        presenter = new MainListPresenter(this);
        presenter.onLoadTransactions();

        return view;
    }

    private void onTransactionSelected(Transaction transactionSelected) {
            TransactionDetailsFragment newFragment = new TransactionDetailsFragment();

            Bundle args = addInfoAboutTransactionToBundle(transactionSelected);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.transactions_fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
    }

    private Bundle addInfoAboutTransactionToBundle(Transaction transactionSelected){
        Bundle args = new Bundle();
        args.putSerializable(TransactionDetailsFragment.TRANSACTION, transactionSelected);
        return args;
    }

    @Override
    public void onTransactionsLoaded(List<Transaction> transactions) {
        Log.i("TAG", transactions.get(0).getLocation());

        transactionAdapter = new TransactionAdapter(context, transactions);

        ListView trasactionsList = (ListView) view.findViewById(R.id.trasactions_list);
        trasactionsList.setAdapter(transactionAdapter);

        trasactionsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long arg){
                Transaction transaction = (Transaction) adapter.getItemAtPosition(position);
                onTransactionSelected(transaction);
            }
        });
    }

    @Override
    public void onTransactionsLoadFailed(String message) {
        Log.e("TAG", message);
    }

}
