package com.example.coolteam.dataprotection.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.adapters.TransactionAdapter;
import com.example.coolteam.dataprotection.mainlist.MainListContract;
import com.example.coolteam.dataprotection.mainlist.MainListPresenter;
import com.example.coolteam.dataprotection.model.Transaction;
import com.example.coolteam.dataprotection.transactionform.TransactionFormActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsListFragment extends Fragment implements MainListContract.View {

    MainListContract.Presenter presenter;

    private Context context;
    private View view;

    TransactionAdapter transactionAdapter;

    @BindView(R.id.add_button)
    FloatingActionButton addBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transactions_list, container, false);

        ButterKnife.bind(this, view);
        context = this.getContext();

        presenter = new MainListPresenter(this);
        presenter.onLoadTransactions();

        //FloatingActionButton addBtn = (Button)

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransactionFormActivity.class);
                startActivity(intent);
            }
        });

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

        ListView transactionsList = (ListView) view.findViewById(R.id.transactions_list);
        transactionsList.setAdapter(transactionAdapter);

        transactionsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
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
