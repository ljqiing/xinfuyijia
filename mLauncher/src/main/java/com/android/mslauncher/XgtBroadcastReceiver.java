
package com.android.mslauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*Xgt*/
import android.content.IntentFilter;

/*
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
*/
/*Xgt*/
	/*
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        registerReceiver(XgtBroadcastReceiver, new IntentFilter("xgtb")); //注册广播接受者
    }
	*/

public class XgtBroadcastReceiver  extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
		
        if (intent.getAction().equals("ENTRY_XGT")) 
		{
            //String packageName = intent.getDataString();
            //Log.i(TAG, "--------add package:-------" + packageName);      
			//buttonxinxi.setBackgroundResource(R.drawable.weathwe);
        } 
		else if (intent.getAction().equals("ENTRY_XGT")) 
		{
            //String packageName = intent.getDataString();
            //Log.i(TAG, "--------remove package-------" + packageName);
        }
        Intent intent1 = new Intent();
        intent1.setAction("network.conect.successfull");
        context.sendBroadcast(intent1);

    }

}
