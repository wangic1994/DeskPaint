package com.example.wangicclock;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import service.FuckingService;
import service.MyLockService;

/**
 * Created by Administrator on 2017/5/16.
 */

public class MyApplication extends Application {
    private static Context context;
    private static final String TAG = "MyApplication";


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Intent service = new Intent(this, MyLockService.class);
        this.startService(service);

        Log.e(TAG, "onCreate: "+getCurrentProcessName());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e(TAG, "onTerminate: "+"wangic" );
    }

    public static Context getContext() {
        return context;
    }

    private String getCurrentProcessName() {
        String currentProcName = "";
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                currentProcName = processInfo.processName;
                break;
            }
        }
        return currentProcName;
    }

}
