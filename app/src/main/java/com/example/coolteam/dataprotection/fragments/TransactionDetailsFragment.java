package com.example.coolteam.dataprotection.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.model.Transaction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionDetailsFragment extends Fragment {

    //public final static String POSITION = "position";
    public final static String TRANSACTION = "transaction";

    @BindView(R.id.transaction_transactionCode)
    TextView transactionCodeTV;
    @BindView(R.id.transaction_status)
    TextView statusTV;
    @BindView(R.id.transaction_date)
    TextView dateTV;
    @BindView(R.id.transaction_requester)
    TextView requesterTV;
    @BindView(R.id.transaction_location)
    TextView locationTV;
    @BindView(R.id.transaction_description)
    TextView descriptionTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            updateTransactionView(
                    (Transaction)args.getSerializable(TRANSACTION)
            );
        }
    }

    public void updateTransactionView(Transaction transaction) {
        Resources res = getResources();
        transactionCodeTV.setText(res.getString(R.string.transactionCode, transaction.getTransactionCode()));
        statusTV.setText(res.getString(R.string.status, transaction.getStatus()));
        dateTV.setText(res.getString(R.string.date, transaction.getDate()));
        requesterTV.setText(res.getString(R.string.requester, transaction.getRequester()));
        locationTV.setText(res.getString(R.string.location, transaction.getLocation()));
        descriptionTV.setText(res.getString(R.string.description, transaction.getDescription()));
    }
}

