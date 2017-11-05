package com.example.coolteam.dataprotection.ui.addtransaction;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coolteam.dataprotection.Constants;
import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.model.Transaction;
import com.example.coolteam.dataprotection.ui.mainlist.MainListActivity;

import java.io.IOException;
import java.sql.Timestamp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTransactionActivity extends AppCompatActivity implements AddTransactionContract.View{

    @BindView(R.id.requester_logo) ImageView requesterLogo;
    @BindView(R.id.add_new_transaction) Button addTransaction;
    @BindView(R.id.location) EditText location;
    @BindView(R.id.requester) EditText requester;
    @BindView(R.id.description) EditText description;
    private Bitmap bitmap;

    private AddTransactionContract.Presenter presenter;
    private ProgressDialog dialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.CHOOSE_PICTURE && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                requesterLogo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        ButterKnife.bind(this);
        presenter = new AddTransactionPresenter(this);
        init();
    }

    private void init() {
        requesterLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*")
                        .setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select picture"),
                        Constants.CHOOSE_PICTURE);
            }
        });
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddTransaction(new Transaction((new Timestamp(System.currentTimeMillis()).getTime()),
                        requester.getText().toString(), location.getText().toString(), "OPEN",
                        "123123", description.getText().toString(), ""), bitmap);
            }
        });
        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait");
        dialog.setMessage("Adding transaction");
        dialog.setCancelable(false);
    }

    @Override
    public Activity getActivity(){
        return this;
    }


    @Override
    public void onTransactionAdded() {
        startActivity(new Intent(AddTransactionActivity.this, MainListActivity.class));
    }

    @Override
    public void onTransactionNotAdded(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.hide();
    }
}
