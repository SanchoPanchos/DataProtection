package com.example.coolteam.dataprotection;

import com.example.coolteam.dataprotection.model.source.remote.TransactionAPI;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Constants {

    private static OkHttpClient.Builder getBuilder() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(logging);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
                return chain.proceed(request);
            }
        });
        return builder;
    }

    public static TransactionAPI getApi(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(TransactionAPI.class);
    }

    private static String BASE_URL = "https://protection-information.herokuapp.com";
}
