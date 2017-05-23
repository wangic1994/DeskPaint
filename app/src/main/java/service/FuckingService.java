package service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.wangicclock.MyApplication;

/**
 * Created by Administrator on 2017/5/17.
 */


public class FuckingService extends Service {
    MyReceiver myReceiver;
    private static final String TAG="FuckingService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver();
    }

    private void registerReceiver(){
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter("MyLockServiceKilled");
        MyApplication.getContext().registerReceiver(myReceiver,intentFilter);
    }
    @Override
    public void onStart(Intent intent, int startId) {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "onReceive: "+"MyLockServiceKilled" );
            Intent i = new Intent(MyApplication.getContext(),MyLockService.class);
            MyApplication.getContext().startService(i);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent i = new Intent("fuckingServiceKilled");
        MyApplication.getContext().sendBroadcast(i);
    }
}
