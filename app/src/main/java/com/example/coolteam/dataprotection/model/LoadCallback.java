package com.example.coolteam.dataprotection.model;

public interface LoadCallback<T> {
    void onLoaded(T data);
    void onNotLoaded(String error);

}
