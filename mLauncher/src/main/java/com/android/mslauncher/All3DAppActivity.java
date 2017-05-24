
package com.android.mslauncher;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/*Xgt*/
import android.content.IntentFilter;


public class All3DAppActivity extends Activity {
    private static final String TAG = "All3DAppActivity";

    private GridView appGridView;

    private List<ResolveInfo> listAllApps;

    private AllAppAdapter mAllAppAdapter;

    private static final int TOTALICON = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allapplication);
        appGridView = (GridView) findViewById(R.id.appgridview);

        initGridView();

        appGridView.setSelection(0);

		/*Xgt*/
		registerReceiver(XgtBroadcastReceiver, new IntentFilter("xgtb")); 
    }

    private void initGridView() {
        final PackageManager packageManager = getPackageManager();
        final Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        listAllApps = packageManager.queryIntentActivities(mIntent, 0);
        mAllAppAdapter = new AllAppAdapter(this, listAllApps);
        appGridView.setAdapter(mAllAppAdapter);
        appGridView.setOnItemClickListener(new AllAppItemcListener());
    }

    private void addItemResult(boolean result) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(getResources().getString(R.string.addtitle));
        if (result == false) {
            ad.setMessage(getResources().getString(R.string.addnotsuccess));

        } else {
            ad.setMessage(getResources().getString(R.string.addsuccess));
        }

        ad.setPositiveButton(getResources().getString(R.string.addconfirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });
        ad.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "++++++++++++++-onKeyDown++++++++++++++");
        if (keyCode == KeyEvent.KEYCODE_0 || keyCode == KeyEvent.KEYCODE_MENU) {
            Log.i(TAG, "******>keycode 0---");
            int position = appGridView.getSelectedItemPosition();
            boolean addResult = (boolean) ((Launcher3DActivity) Launcher3DActivity.mContext)
                    .addIcon((String) listAllApps.get(position).loadLabel(getPackageManager()),
                            listAllApps.get(position).activityInfo.packageName,
                            listAllApps.get(position).activityInfo.name);

            addItemResult(addResult);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class AllAppItemcListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ResolveInfo appInfo = (ResolveInfo) parent.getItemAtPosition(position);

            Intent mIntent = All3DAppActivity.this.getPackageManager().getLaunchIntentForPackage(
                    appInfo.activityInfo.packageName);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            try {
                All3DAppActivity.this.startActivity(mIntent);

            } catch (ActivityNotFoundException anf) {
                Log.i(TAG,
                        "All3DAppActivity.AllAppItemcListener.onItemClick.ActivityNotFoundException");
                Toast.makeText(
                        All3DAppActivity.this,
                        All3DAppActivity.this.getResources().getString(
                                R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
            }

        }

    }

private BroadcastReceiver XgtBroadcastReceiver = new BroadcastReceiver(){

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
    }
};

	
}
