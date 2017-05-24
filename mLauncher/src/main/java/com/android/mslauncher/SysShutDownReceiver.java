
package com.android.mslauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SysShutDownReceiver extends BroadcastReceiver {
    private static final String TAG = "SysShutDownReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.ACTION_SHUTDOWN")) {
            Log.i(TAG, "++++++++++++++++++++++ACTION_SHUTDOWN:");
            if ((LauncherActivity) LauncherActivity.msContext != null) {
                ((LauncherActivity) LauncherActivity.msContext).setSysShutdown();
            }
        }
    }

}
