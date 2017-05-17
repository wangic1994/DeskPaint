package service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.wangicclock.MyApplication;
import com.example.wangicclock.R;

import view.DeskView;

/**
 * Created by Administrator on 2017/5/16.
 */

public class MyLockService extends Service {
    private View lockView;
    private DeskView deskView;
    public static boolean hasAddView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public ComponentName startService(Intent service) {
        return super.startService(service);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = Service.START_FLAG_RETRY;
        lockView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.activity_main,null);
        deskView = (DeskView) lockView.findViewById(R.id.haha);
        WindowManager mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        int flags1 = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        params.flags = flags1;
        params.format = PixelFormat.TRANSLUCENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE ;
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT ;//这里可以改为系统级别的弹窗，哈哈
        if(!hasAddView) {
            mWindowManager.addView(lockView,params);
            hasAddView = true;
        }
        MyReceiver myReceiver = new MyReceiver();
        IntentFilter i = new IntentFilter("wangic");
        this.registerReceiver(myReceiver,i);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Intent i = new Intent("wangic");
        sendBroadcast(i);
        super.onDestroy();
    }

    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Intent i = new Intent(MyApplication.getContext(),MyLockService.class);
            MyApplication.getContext().startService(i);
        }
    }
}
