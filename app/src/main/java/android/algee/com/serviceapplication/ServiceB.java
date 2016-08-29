package android.algee.com.serviceapplication;


import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Debug;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

public class ServiceB extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(MyApplication.TAG, "ServiceB onCreate() called: PID=" + Process.myPid());
//        rackUpTheBill();
        return super.onStartCommand(intent, flags, startId);
    }

    private void rackUpTheBill() {
        new Thread() {
            @Override
            public void run() {
                LinkedList<Object> objects = new LinkedList<Object>();
                Runtime runtime = Runtime.getRuntime();
                try {
                    Log.d(MyApplication.TAG, String.format(Locale.US, "initial measurement B: FREE=%d," +
                                    " MAX=%d, TOTAL=%d", runtime.freeMemory(), runtime.maxMemory(),
                            runtime.totalMemory()));
                    sleep(5000);
                    while (true) {
                        byte[] b = new byte[1024];
                        objects.add(b);
                        Log.d(MyApplication.TAG, String.format(Locale.US, "B: FREE=%d," +
                                        " MAX=%d", runtime.freeMemory(), runtime.maxMemory(),
                                runtime.totalMemory()));
                        sleep(300);
                    }
                } catch (Throwable t) {
                    Log.e(MyApplication.TAG, String.format(Locale.US, "B: FREE=%d," +
                                    " MAX=%d", runtime.freeMemory(), runtime.maxMemory(),
                            runtime.totalMemory()), t);
                }
            }
        }.start();
    }
}
