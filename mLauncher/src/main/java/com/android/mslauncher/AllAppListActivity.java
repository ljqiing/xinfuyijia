
package com.android.mslauncher;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.mstar.android.tv.TvCommonManager;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;

public class AllAppListActivity extends Activity {
    private static final String TAG = "AllAppListActivity";

    private GridView appGridView;

    private List<ResolveInfo> listAllApps;

    private AllAppAdapter mAllAppAdapter;

    private static final int TOTALICON = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allapplication);
        appGridView = (GridView) findViewById(R.id.appgridview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initGridView();
        appGridView.setSelection(0);
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

    private void addItemResult(int page) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(AllAppListActivity.this.getResources().getString(R.string.addtitle));
        Log.i(TAG, "++++++++++++++-addItemResult page : " + page);
        if (page >= 5) {
            if (page == TOTALICON) {
                ad.setMessage(AllAppListActivity.this.getResources().getString(
                        R.string.all_page_is_full));
            } else {
                int i = page - TOTALICON - 1;
                ad.setMessage(AllAppListActivity.this.getResources().getString(
                        R.string.the_page_have_exist)
                        + i);
            }
        } else {
            ad.setMessage(AllAppListActivity.this.getResources().getString(
                    R.string.the_app_have_add)
                    + page + AllAppListActivity.this.getResources().getString(R.string.page));
        }

        ad.setPositiveButton(AllAppListActivity.this.getResources().getString(R.string.addconfirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });
        ad.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "++++++++++++++-onKeyDown++++++++++++++");
        if (keyCode == KeyEvent.KEYCODE_0) {
            Log.i(TAG, "******>keycode 0---");
            int position = appGridView.getSelectedItemPosition();
            if (position < 0)
                return true;
            int resultpage = ((LauncherActivity) LauncherActivity.msContext).addItem(listAllApps
                    .get(position));
            if (resultpage < 0) {
                AlertDialog.Builder ad = new AlertDialog.Builder(this);
                ad.setTitle(AllAppListActivity.this.getResources().getString(R.string.addtitle));
                ad.setMessage(AllAppListActivity.this.getResources().getString(
                        R.string.all_page_is_full));
                ad.setPositiveButton(
                        AllAppListActivity.this.getResources().getString(R.string.addconfirm),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {

                            }
                        });
                ad.show();
            } else {
                addItemResult(resultpage);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void changeInputSource(String packName) {

        if (packName != null) {

            if (packName.contentEquals("com.mstar.tv.tvplayer.ui")
                    || packName.contentEquals("mstar.factorymenu.ui")
                    || packName.contentEquals("com.tvos.pip")
                    || packName.contentEquals("com.mstar.tvsetting.hotkey")
                    || packName.contentEquals("com.mstar.appdemo")/*
                                                                   * ||
                                                                   * packName.
                                                                   * contentEquals
                                                                   * (
                                                                   * "com.android.settings"
                                                                   * )
                                                                   */) {
                Log.i(TAG, "------------TV AP");
            } else {

                // ITvServiceServer tvService =
                // ITvServiceServer.Stub.asInterface(ServiceManager.checkService(Context.TV_SERVICE));
                // if (tvService == null) {
                // Log.w(TAG, "Unable to find ITvService interface.");
                // }

                // try {
                //
                //
                //
                // ITvServiceServerCommon commonService =
                // tvService.getCommonManager();
                //
                // try {
                // commonService.SetInputSource(EN_INPUT_SOURCE_TYPE.E_INPUT_SOURCE_STORAGE);
                //
                //
                // } catch (RemoteException e) {
                //
                // e.printStackTrace();
                //
                // }
                //
                // } catch (RemoteException e) {
                //
                // e.printStackTrace();
                //
                // }
                TvCommonManager tvCommonManager = TvCommonManager.getInstance();
                tvCommonManager.setInputSource(EnumInputSource.E_INPUT_SOURCE_STORAGE);
            }

        }
    }

    private class AllAppItemcListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ResolveInfo appInfo = (ResolveInfo) parent.getItemAtPosition(position);

            Intent mIntent = AllAppListActivity.this.getPackageManager().getLaunchIntentForPackage(
                    appInfo.activityInfo.packageName);
            if (mIntent != null) {
                changeInputSource(appInfo.activityInfo.packageName);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                try {
                    AllAppListActivity.this.startActivity(mIntent);

                } catch (ActivityNotFoundException anf) {
                    Log.i(TAG,
                            "AllAppListActivity.AllAppItemcListener.onItemClick.ActivityNotFoundException");
                    Toast.makeText(
                            AllAppListActivity.this,
                            AllAppListActivity.this.getResources().getString(
                                    R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.i(TAG, "AllAppListActivity.AllAppItemcListener.onItemClick.[mIntent != null]");
                Toast.makeText(
                        AllAppListActivity.this,
                        AllAppListActivity.this.getResources().getString(
                                R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
            }

        }

    }
}
