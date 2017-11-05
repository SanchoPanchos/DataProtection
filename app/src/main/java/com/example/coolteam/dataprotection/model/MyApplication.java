package com.example.coolteam.dataprotection.model;

import android.app.Application;

import io.realm.Realm;

public class MyApplication extends Application {

    //TODO красиво выводить дату
    //TODO анимацию между фрагментами
    //TODO доделать БД (обновить реалм после каждого запроса и не только)
    //TODO кеширование картинок Glide
    //TODO не пересоздавать фрагменты
    //TODO документация методов
    //TODO редактирование тразакции

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
