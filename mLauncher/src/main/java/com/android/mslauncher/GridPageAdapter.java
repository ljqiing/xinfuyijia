
package com.android.mslauncher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridPageAdapter extends BaseAdapter {

    private static final String TAG = "GridPageAdapter";

    private List<ResolveInfo> mList;

    private List<MstarItemInfo> msList;

    private Context mContext;

    private PackageManager pm;

    // final static private String TAG = "GridPageAdapter";
    private static final int PAGE_APP_NUM = 15;

    // table name
    private static final String TABLE_NAME = "mstaritem";

    // content "content://"
    private static final String CONTENT_LABEL = "content://";

    // slash
    private static final String MSTAR_SLASH = "/";

    // authority
    private static final String MSTAR_AUTHORITY = "com.mstar.xy.MstarDatabaseProvider";

    // content uri
    private static final String MSTAR_URI = CONTENT_LABEL + MSTAR_AUTHORITY + MSTAR_SLASH
            + TABLE_NAME;

    private static final String SCENE = "scene";

    private static final String AGENT = "agent";

    private static final String OBJECT = "object";

    private static final String TITLE = "title";

    private static final String CLASSNAME = "className";

    private static final String PACKAGENAME = "packageName";

    private static final String LEVEL = "level";

    private static final String PAGE = "page";

    private static final String ICONINDEX = "iconindex";

    public GridPageAdapter(Context c) {
        mContext = c;
    }

    public GridPageAdapter(Context c, List<ResolveInfo> list, int pageIdx) {
        mContext = c;
        msList = new ArrayList<MstarItemInfo>();
        Cursor cursor = null;
        String[] mPageIdx = new String[] {
            "" + pageIdx
        };

        cursor = mContext.getContentResolver().query(Uri.parse(MSTAR_URI), null, "page=?",
                mPageIdx, null);
        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    MstarItemInfo item = new MstarItemInfo();
                    item.setScene(cursor.getString(cursor.getColumnIndex(SCENE)));
                    item.setAgent(cursor.getString(cursor.getColumnIndex(AGENT)));
                    item.setObject(cursor.getString(cursor.getColumnIndex(OBJECT)));
                    item.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                    item.setPackageName(cursor.getString(cursor.getColumnIndex(PACKAGENAME)));
                    item.setClassName(cursor.getString(cursor.getColumnIndex(CLASSNAME)));
                    item.setLevel(cursor.getInt(cursor.getColumnIndex(LEVEL)));
                    item.setPage(cursor.getInt(cursor.getColumnIndex(PAGE)));
                    item.setIconIndex(cursor.getInt(cursor.getColumnIndex(ICONINDEX)));

                    msList.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        pm = c.getPackageManager();
        mList = new ArrayList<ResolveInfo>();

        for (int i = 0; i < msList.size(); i++) {

            for (int j = 0; j < list.size(); j++) {
                if ((msList.get(i).getClassName()).equals(list.get(j).activityInfo.name)
                        && (msList.get(i).getPackageName())
                                .equals(list.get(j).activityInfo.packageName)) {
                    mList.add(list.get(j));
                }
            }
        }

        Log.i(TAG, "============================mList.size" + mList.size());
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public Object getItem(int position) {

        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ResolveInfo appInfo = mList.get(position);
        AppItem mAppItem;
        if (convertView == null) {
            mAppItem = new AppItem();
            View mView = LayoutInflater.from(mContext).inflate(R.layout.appicon, null);

            mAppItem.mAppIcon = (ImageView) mView.findViewById(R.id.appicon);
            mAppItem.mAppTitle = (TextView) mView.findViewById(R.id.apptitle);

            mView.setTag(mAppItem);

            convertView = mView;
        } else {
            mAppItem = (AppItem) convertView.getTag();
        }

        mAppItem.mAppIcon.setBackgroundDrawable(appInfo.loadIcon(pm));
        mAppItem.mAppTitle.setText(appInfo.loadLabel(pm));
        mAppItem.mAppTitle.setTextSize(15.0f);
        return convertView;
    }

    public void addItemIcon(ResolveInfo rsi, int pageidx) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCENE, "");
        contentValues.put(AGENT, "");
        contentValues.put(OBJECT, "");
        contentValues.put(TITLE, "");

        contentValues.put(PACKAGENAME, rsi.activityInfo.packageName);
        contentValues.put(CLASSNAME, rsi.activityInfo.name);
        contentValues.put(LEVEL, 0);
        contentValues.put(PAGE, pageidx);
        contentValues.put(ICONINDEX, mList.size());
        mContext.getContentResolver().insert(Uri.parse(MSTAR_URI), contentValues);

        mList.add(rsi);
        notifyDataSetChanged();
    }

    public void removeItemIcon(int position) {
        if (position != -1) {
        String packagename = mList.get(position).activityInfo.packageName;
        if (packagename != null) {
            mContext.getContentResolver().delete(Uri.parse(MSTAR_URI), "packageName=?",
                    new String[] {
                        packagename
                    });
            mList.remove(position);
            notifyDataSetChanged();
        }
    }
    }

    public int getPosFromPackName(String packagename) {

        for (int i = 0; i < getCount(); i++) {
            // Log.i(TAG, "**********" + packagename +"----------" +
            // mList.get(i).activityInfo.packageName);
            if (packagename != null
                    && packagename.equals("package:" + mList.get(i).activityInfo.packageName)) {
                return i;
            }
        }
        return -1;
    }

    // App have icon and title
    private class AppItem {
        ImageView mAppIcon;

        TextView mAppTitle;
    }

    public boolean findItem(ResolveInfo rsi) {
        for (int i = 0; i < mList.size(); i++) {
            if (rsi.activityInfo.name.equals(mList.get(i).activityInfo.name)) {
                return true;
            }
        }
        return false;
    }

    public void echoDB() {

        Cursor c = mContext.getContentResolver()
                .query(Uri.parse(MSTAR_URI), null, null, null, null);
        if (c != null) {
            Log.v(TAG, "CURSOR SIZE " + c.getCount());
            while (c.moveToNext()) {
                Log.v(TAG,
                        "SCENE " + c.getString(c.getColumnIndex(SCENE)) + " AGENT "
                                + c.getString(c.getColumnIndex(AGENT)) + " OBJECT "
                                + c.getString(c.getColumnIndex(OBJECT)) + " TITLE "
                                + c.getString(c.getColumnIndex(TITLE)) + " PAKCAGE "
                                + c.getString(c.getColumnIndex(PACKAGENAME)) + " CLASSNAME "
                                + c.getString(c.getColumnIndex(CLASSNAME)) + " LEVEL "
                                + c.getInt(c.getColumnIndex(LEVEL)) + " PAGE "
                                + c.getInt(c.getColumnIndex(PAGE)) + " INDEX "
                                + c.getInt(c.getColumnIndex(ICONINDEX)));
            }
        }
    }
}
