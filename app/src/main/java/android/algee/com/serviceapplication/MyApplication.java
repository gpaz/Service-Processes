package android.algee.com.serviceapplication;

import android.app.Application;
import android.os.Process;
import android.util.Log;


public class MyApplication extends Application {

    public static final String TAG = "ServiceApplication";

    @Override
    public void onCreate() {
        Log.e(TAG, "Application onCreate() called: PID=" + Process.myPid());
        super.onCreate();
    }
}
