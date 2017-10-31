package com.example.coolteam.dataprotection.transactionform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coolteam.dataprotection.R;
import com.example.coolteam.dataprotection.ui.adapters.TransactionAdapter;
import com.example.coolteam.dataprotection.ui.mainlist.MainActivity;

public class TransactionFormActivity extends AppCompatActivity {

    TransactionAdapter transactionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_form);



    }

    public void onClick_Save(View v)
    {

        EditText edit_text   = (EditText)findViewById(R.id.requester);
        String requester = edit_text.getText().toString();
        edit_text   = (EditText)findViewById(R.id.location);
        String location = edit_text.getText().toString();
        edit_text   = (EditText)findViewById(R.id.description);
        String description = edit_text.getText().toString();

        if (requester.length() < 1 || location.length() <1 )
        {
            Toast.makeText(TransactionFormActivity.this,"Requester or location cannot be empty", Toast.LENGTH_SHORT).show();}
        else
        {
         //  transactionAdapter.addItem(requester,location,description); TODO
        //   transactionAdapter.notifyDataSetChanged();
            Toast.makeText(TransactionFormActivity.this,"Transaction added", Toast.LENGTH_SHORT).show();
            this.finish();}
    }
}
