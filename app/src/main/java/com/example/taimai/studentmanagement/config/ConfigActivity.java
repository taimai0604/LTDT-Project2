package com.example.taimai.studentmanagement.config;

import android.app.Application;

import io.realm.Realm;


public class ConfigActivity  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //init realm
        Realm.init(this);
    }


}