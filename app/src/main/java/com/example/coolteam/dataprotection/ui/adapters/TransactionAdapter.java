package com.example.coolteam.dataprotection.ui.adapters;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.coolteam.dataprotection.model.AdapterCallback;
import com.example.coolteam.dataprotection.model.AdapterDeleteCallback;
import com.example.coolteam.dataprotection.model.GlideApp;
import com.example.coolteam.dataprotection.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> transactions;
    private AdapterCallback callback;

    public TransactionAdapter(List<Transaction> transactions, AdapterCallback callback) {
        this.transactions = transactions;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.date.setText("Date:" + transactions.get(position).getDateLong());
        holder.requster.setText("Requester:" + transactions.get(position).getRequester());
        GlideApp.with(holder.itemView).asBitmap().error(R.drawable.img_small).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                holder.image.setImageBitmap(ConstantFunctions.getCircleBitmap(resource));
                return true;
            }
        }).load(transactions.get(position).getRequesterLogo()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView requster;
        private TextView date;
        private ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            requster = itemView.findViewById(R.id.transaction_card_requester);
            date = itemView.findViewById(R.id.transaction_card_date);
            image = itemView.findViewById(R.id.transaction_card_image);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            callback.onClick(getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            callback.onLongClick(getLayoutPosition(), new AdapterDeleteCallback(){
                @Override
                public void onDeleteItem(int position) {
                    transactions.remove(position);
                }
            });
            return true;
        }
    }

}
