
package com.android.mslauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class App3DReceiver extends BroadcastReceiver {
    private static final String TAG = "App3DReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            Log.i(TAG, "--------add package:-------" + packageName);
        } else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            Log.i(TAG, "--------remove package-------" + packageName);
            if (packageName != null && (Launcher3DActivity) Launcher3DActivity.mContext != null) {
                ((Launcher3DActivity) Launcher3DActivity.mContext).deleteIcon(packageName);
            }

        }

    }

}
