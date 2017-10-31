package com.example.coolteam.dataprotection.ui.addtransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.coolteam.dataprotection.ConstantFunctions;
import com.example.coolteam.dataprotection.Constants;
import com.example.coolteam.dataprotection.R;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTransactionActivity extends AppCompatActivity {

    @BindView(R.id.requester_logo) ImageView requesterLogo;
    @BindView(R.id.add_new_transaction) Button addTransaction;
    private Bitmap bitmap;

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
        setContentView(R.layout.activity_transaction_form);
        ButterKnife.bind(this);
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
                String imagepath = ConstantFunctions.saveBitmap(bitmap, AddTransactionActivity.this, "images",
                        "image.jpg");
                File imageFile = new File(imagepath);
                Log.i("TAG", "imagefile packName (update with image)" + imageFile.getName());

                RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
                MultipartBody.Part image = MultipartBody.Part.createFormData("upload", imageFile.getName(), reqFile);
                Constants.getApi().uploadImage(image).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("TAG", response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                    }
                });
            }
        });
    }
}
