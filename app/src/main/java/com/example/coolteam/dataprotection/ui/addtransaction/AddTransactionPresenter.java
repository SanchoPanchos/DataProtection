package com.example.coolteam.dataprotection.ui.addtransaction;

import android.graphics.Bitmap;

import com.example.coolteam.dataprotection.ConstantFunctions;
import com.example.coolteam.dataprotection.model.ImageUploadResponse;
import com.example.coolteam.dataprotection.model.LoadCallback;
import com.example.coolteam.dataprotection.model.Transaction;
import com.example.coolteam.dataprotection.model.source.local.RealmManager;
import com.example.coolteam.dataprotection.model.source.remote.TransactionManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class AddTransactionPresenter implements AddTransactionContract.Presenter{

    private AddTransactionContract.View view;
    private TransactionManager transactionManager;
    private RealmManager realmManager;

    AddTransactionPresenter(AddTransactionContract.View view) {
        this.view = view;
        transactionManager = new TransactionManager();
        this.realmManager = new RealmManager();
    }

    @Override
    public void onAddTransaction(final Transaction transaction, Bitmap bitmap) {
        view.showDialog();
        //TODO проверка на интернет
        if(bitmap != null) {
            String imagepath = ConstantFunctions.saveBitmap(ConstantFunctions.resizeBitmap(bitmap), view.getActivity(), "images",
                    "image.jpg");
            File imageFile = new File(imagepath);

            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
            MultipartBody.Part image = MultipartBody.Part.createFormData("upload", imageFile.getName(), reqFile);
            transactionManager.uploadImage(image, new LoadCallback<ImageUploadResponse>() {
                @Override
                public void onLoaded(ImageUploadResponse data) {
                    transaction.setRequesterLogo(data.getData().getImgUrl());
                    transactionManager.addTransaction(transaction, new LoadCallback() {
                        @Override
                        public void onLoaded(Object data) {
                            view.hideDialog();
                            view.onTransactionAdded();
                        }

                        @Override
                        public void onNotLoaded(String error) {
                            view.hideDialog();
                            view.onTransactionNotAdded(error);
                        }
                    });
                    realmManager.addTransaction(transaction, new LoadCallback() {
                        @Override
                        public void onLoaded(Object data) {
                            view.hideDialog();
                            view.onTransactionAdded();
                        }

                        @Override
                        public void onNotLoaded(String error) {
                            view.hideDialog();
                            view.onTransactionNotAdded(error);
                        }
                    });
                }

                @Override
                public void onNotLoaded(String error) {
                    view.hideDialog();
                    view.onTransactionNotAdded(error);
                }
            });
        }
        else{
            transactionManager.addTransaction(transaction, new LoadCallback() {
                @Override
                public void onLoaded(Object data) {
                    view.hideDialog();
                    view.onTransactionAdded();
                }

                @Override
                public void onNotLoaded(String error) {
                    view.hideDialog();
                    view.onTransactionNotAdded(error);
                }
            });
            realmManager.addTransaction(transaction, new LoadCallback() {
                @Override
                public void onLoaded(Object data) {
                    view.hideDialog();
                    view.onTransactionAdded();
                }

                @Override
                public void onNotLoaded(String error) {
                    view.hideDialog();
                    view.onTransactionNotAdded(error);
                }
            });
        }

    }
}
