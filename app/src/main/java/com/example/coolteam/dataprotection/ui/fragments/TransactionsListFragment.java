package com.example.coolteam.dataprotection.ui.fragments;

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
import android.widget.ListView;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.ui.adapters.TransactionAdapter;
import com.example.coolteam.dataprotection.ui.mainlist.MainListContract;
import com.example.coolteam.dataprotection.ui.mainlist.MainListPresenter;
import com.example.coolteam.dataprotection.model.Transaction;
import com.example.coolteam.dataprotection.ui.addtransaction.AddTransactionActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsListFragment extends Fragment implements MainListContract.View {

    private MainListContract.Presenter presenter;
    private View view;

    @BindView(R.id.add_button) FloatingActionButton addBtn;

    @BindView(R.id.transactions_list) ListView transactionsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transactions_list, container, false);

        ButterKnife.bind(this, view);


        presenter = new MainListPresenter(this);
        presenter.onLoadTransactions();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTransactionActivity.class);
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
        transactionsList.setAdapter(new TransactionAdapter(this.getActivity(), transactions));

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
