package com.example.coolteam.dataprotection.ui.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.coolteam.dataprotection.ConstantFunctions;
import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.model.GlideApp;
import com.example.coolteam.dataprotection.model.Transaction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionDetailsFragment extends Fragment {

    public final static String TRANSACTION = "transaction";

    @BindView(R.id.transaction_transactionCode) TextView transactionCodeTV;
    @BindView(R.id.transaction_status) TextView statusTV;
    @BindView(R.id.transaction_date) TextView dateTV;
    @BindView(R.id.transaction_requester) TextView requesterTV;
    @BindView(R.id.transaction_location) TextView locationTV;
    @BindView(R.id.transaction_description) TextView descriptionTV;
    @BindView(R.id.transaction_image) ImageView image;

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
        final Resources res = getResources();
        transactionCodeTV.setText(res.getString(R.string.transactionCode, transaction.getTransactionCode()));
        statusTV.setText(res.getString(R.string.status, transaction.getStatus()));
        dateTV.setText(res.getString(R.string.date, transaction.getDateLong()));
        requesterTV.setText(res.getString(R.string.requester, transaction.getRequester()));
        locationTV.setText(res.getString(R.string.location, transaction.getLocation()));
        descriptionTV.setText(res.getString(R.string.description, transaction.getDescription()));
        GlideApp.with(this).asBitmap().error(R.drawable.img_big).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                image.setImageBitmap(ConstantFunctions.getCircleBitmap(resource));
                return true;
            }
        }).load(transaction.getRequesterLogo()).into(image);
    }
}

