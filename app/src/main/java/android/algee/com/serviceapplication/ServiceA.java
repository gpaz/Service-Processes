package android.algee.com.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.LinkedList;
import java.util.Locale;

public class ServiceA extends Service {
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
        Log.e(MyApplication.TAG, "ServiceA onCreate() called: PID=" + Process.myPid());
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
                    Log.d(MyApplication.TAG, String.format(Locale.US, "initial measurement A: FREE=%d," +
                                    " MAX=%d, TOTAL=%d", runtime.freeMemory(), runtime.maxMemory(),
                            runtime.totalMemory()));
                    sleep(5000);
                    while (true) {
                        byte[] b = new byte[1024*1024];
                        objects.add(b);
                        Log.d(MyApplication.TAG, String.format(Locale.US, "A: FREE=%d," +
                                        " MAX=%d", runtime.freeMemory(), runtime.maxMemory(),
                                runtime.totalMemory()));
                        sleep(300);
                    }
                } catch (Throwable t) {
                    Log.e(MyApplication.TAG, String.format(Locale.US, "A: FREE=%d," +
                                    " MAX=%d", runtime.freeMemory(), runtime.maxMemory(),
                            runtime.totalMemory()), t);
                }
            }
        }.start();
    }
}
