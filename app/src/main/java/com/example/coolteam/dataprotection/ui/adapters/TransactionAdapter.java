package com.example.coolteam.dataprotection.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater lInflater;
    private List<Transaction> objects;
    private List<View> views;

    @BindView(R.id.transaction_requester)
    TextView requesterTV;
    @BindView(R.id.transaction_location)
    TextView locationTV;

    public TransactionAdapter(Activity activity, List<Transaction> transactions) {
        this.activity = activity;
        objects = transactions;
        views = new ArrayList<>();
        lInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try{
            return views.get(position);
        }catch (IndexOutOfBoundsException e){
            convertView = lInflater.inflate(R.layout.transaction_item, null, true);
            ButterKnife.bind(this, convertView);

            Transaction transaction = objects.get(position);

            Resources res = convertView.getResources();
            requesterTV.setText(res.getString(R.string.requester, transaction.getRequester()));
            locationTV.setText(res.getString(R.string.location, transaction.getLocation()));

            return convertView;
        }


    }

}
