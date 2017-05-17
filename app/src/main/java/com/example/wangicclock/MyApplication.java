package com.example.wangicclock;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import service.MyLockService;

/**
 * Created by Administrator on 2017/5/16.
 */

public class MyApplication extends Application {
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Intent service = new Intent(this, MyLockService.class);
        this.startService(service);

    }

    public static Context getContext() {
        return context;
    }

}
