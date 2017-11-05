package com.example.coolteam.dataprotection.ui.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.model.AdapterCallback;
import com.example.coolteam.dataprotection.model.AdapterDeleteCallback;
import com.example.coolteam.dataprotection.ui.adapters.TransactionAdapter;
import com.example.coolteam.dataprotection.ui.addtransaction.AddTransactionActivity;
import com.example.coolteam.dataprotection.ui.mainlist.MainListContract;
import com.example.coolteam.dataprotection.ui.mainlist.MainListPresenter;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsListFragment extends Fragment implements MainListContract.View {

    @BindView(R.id.transactions_list) RecyclerView transactionsList;
    @BindView(R.id.add_button) FloatingActionButton addBtn;
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefresh;

    private MainListContract.Presenter presenter;
    private TransactionAdapter transactionAdapter;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions_list, container, false);
        ButterKnife.bind(this, view);
        dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading transactions...");
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onLoadTransactions();
            }
        });

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
    public void onTransactionsLoaded(final List<Transaction> transactions) {
        transactionsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        transactionAdapter = new TransactionAdapter(transactions, new AdapterCallback() {
            @Override
            public void onClick(int position) {
               onTransactionSelected(transactions.get(position));
            }

            @Override
            public void onLongClick(final int position, final AdapterDeleteCallback callback) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setItems(new String[]{"Edit"
                        , "Delete"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                break;
                            case 1:
                                presenter.onDeleteTransaction(transactions.get(position).getId());
                                callback.onDeleteItem(position);
                                transactionAdapter.notifyItemRemoved(position);
                                transactionAdapter.notifyItemRangeChanged(position, transactions.size()-1);
                                transactionsList.setAdapter(transactionAdapter);
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
        transactionsList.setAdapter(transactionAdapter);


    }

    @Override
    public void onTransactionsLoadFailed(String message) {
        Log.e("TAG", message);
    }

    @Override
    public void onTransactionDeleted() {

    }

    @Override
    public void onTransactionNotDeleted(String error) {

    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.hide();
        swipeRefresh.setRefreshing(false);
    }

}

