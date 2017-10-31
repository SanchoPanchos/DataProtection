package com.example.coolteam.dataprotection.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.ui.adapters.TransactionAdapter;
import com.example.coolteam.dataprotection.ui.mainlist.MainActivity;
import com.example.coolteam.dataprotection.ui.mainlist.MainListContract;
import com.example.coolteam.dataprotection.ui.mainlist.MainListPresenter;
import com.example.coolteam.dataprotection.model.Transaction;
import com.example.coolteam.dataprotection.transactionform.TransactionFormActivity;

import java.util.ArrayList;
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


        hideButtons();
        TransactionDetailsFragment newFragment = new TransactionDetailsFragment();

        Bundle args = addInfoAboutTransactionToBundle(transactionSelected);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.transactions_fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    //MENU
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

     super.onCreateContextMenu(menu,v,menuInfo);
        menu.add(Menu.NONE, 0, Menu.NONE, "Edit");
        menu.add(Menu.NONE, 1, Menu.NONE, "Remove");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

       // Log.v("","Position" +transactionsList.getId());
        switch (item.getItemId()) {
            case 0:
               // doEdit();

                return true;
            case 1:

                transactionAdapter.getItem(info.position);
                //  doDelete();
                transactionAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    private void hideButtons()
    {
        ImageButton button_del = (ImageButton) view.findViewById(R.id.btn_delete);
        ImageButton button_ed = (ImageButton) view.findViewById(R.id.btn_edit);

        button_del.setVisibility(View.GONE);

        button_ed.setVisibility(View.GONE);
    }
    ///////////////

    private Bundle addInfoAboutTransactionToBundle(Transaction transactionSelected){
        Bundle args = new Bundle();
        args.putSerializable(TransactionDetailsFragment.TRANSACTION, transactionSelected);
        return args;
    }

    @Override
    public void onTransactionsLoaded(List<Transaction> transactions) {
        Log.i("TAG", transactions.get(0).getLocation());

        transactionAdapter = new TransactionAdapter(this.getActivity(), transactions);

        final ListView transactionsList = (ListView) view.findViewById(R.id.transactions_list);
        transactionsList.setAdapter(transactionAdapter);

        transactionsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long arg){
                Transaction transaction = (Transaction) adapter.getItemAtPosition(position);
                onTransactionSelected(transaction);
            }
        });
        registerForContextMenu(transactionsList);

// LONG TAP
        /*
        transactionsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?>adapter,View v, int position, long arg){
                Transaction transaction = (Transaction) adapter.getItemAtPosition(position);
                Log.v("","Tap");
                return onTransactionLongTap(position, transactionsList);

            }
        }); */
    }

    @Override
    public void onTransactionsLoadFailed(String message) {
        Log.e("TAG", message);
    }

}

