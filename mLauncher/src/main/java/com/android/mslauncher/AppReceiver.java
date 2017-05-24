
package com.android.mslauncher;

import com.android.mslauncher.LauncherActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class AppReceiver extends BroadcastReceiver {
    private static final String TAG = "AppReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            Log.i(TAG, "--------add package:-------" + packageName);
        } else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            Log.i(TAG, "--------remove package-------" + "PACKAGE_REMOVED" + packageName);
            if ((LauncherActivity) LauncherActivity.msContext != null) {
                GridPageAdapter gda = ((LauncherActivity) LauncherActivity.msContext)
                        .getGPageAdapter(packageName);

                if (gda != null) {
                    int idx = gda.getPosFromPackName(packageName);
                    Log.i(TAG, "++++++++++++++++++++++idx:" + idx);
                    if (idx != -1) {
                        gda.removeItemIcon(idx);
                    }
                }
            }
        }
    }

}
