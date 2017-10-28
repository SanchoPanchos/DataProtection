package com.example.coolteam.dataprotection.model;

public interface Callback<T> {
    void onLoadStarted();
    void onLoaded(T data);
    void onNotLoaded(String error);

}
