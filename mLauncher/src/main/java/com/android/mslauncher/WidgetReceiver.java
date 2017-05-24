
package com.android.mslauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WidgetReceiver extends BroadcastReceiver {
    private static String reAppPackageName = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        reAppPackageName = intent.getStringExtra("appPackageName");
    }

    public static String getReAppPackageName() {
        return reAppPackageName;
    }

}
