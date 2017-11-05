package com.example.coolteam.dataprotection.model;

public interface AdapterCallback {
    void onClick(int position);
    void onLongClick(int position, AdapterDeleteCallback callback);
}
