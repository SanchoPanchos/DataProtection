package com.example.coolteam.dataprotection.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Transaction> objects;

    @BindView(R.id.transaction_requester)
    TextView requesterTV;
    @BindView(R.id.transaction_location)
    TextView locationTV;

    public TransactionAdapter(Context context, List<Transaction> transactions) {
        ctx = context;
        objects = transactions;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.transaction_item, parent, false);
        }

        ButterKnife.bind(this, view);

        Transaction transaction = getTransaction(position);

        Resources res = view.getResources();
        requesterTV.setText(res.getString(R.string.requester, transaction.getRequester()));
        locationTV.setText(res.getString(R.string.location, transaction.getLocation()));

        return view;
    }

 //TO DO
   // public void deleteItem(int position){}
 /*
    public void addItem(String requester,String location, String description )
    {
        Transaction newTransaction = new Transaction(111, 12434,requester,location,"STATUS","transactionCode",description,"requesterLogo");

    }
*/
    Transaction getTransaction(int position) {
        return ((Transaction) getItem(position));
    }
}
