
package com.android.mslauncher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mstar.tvframework.Agent;
import com.mstar.tvframework.Entity;

import android.content.ActivityNotFoundException;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.drm.DrmStore.RightsStatus;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.text.GetChars;
import android.util.Log;
import android.widget.Toast;

public class PageIcon3DAdapter {

    private static final String TAG = "PageIcon3DAdapter";

    public List<MstarItemInfo> msList;

    private Context mContext;

    private PackageManager pm;

    private List<ResolveInfo> listApps;

    private int myAppCount;

    public ArrayList<String> removeEntityList;

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

    private static final String ID = "_id";

    private static final String SCENE = "scene";

    private static final String AGENT = "agent";

    private static final String OBJECT = "object";

    private static final String TITLE = "title";

    private static final String CLASSNAME = "className";

    private static final String PACKAGENAME = "packageName";

    private static final String LEVEL = "level";

    private static final String PAGE = "page";

    private static final String ICONINDEX = "iconindex";

    private final int BITMAP_H = 90;

    private final int BITMAP_W = 100;

    private final int ICON_W = 256;

    private final int ICON_H = 256;

    // file suffix, .PNG
    private static final String SUFIXX_PNG = ".png";

    // for storage
    private static final String PATH_SDCARD = "/mnt/usb/sda1/";

    // max quality
    private static final int MAX_QUALITY = 100;

    public PageIcon3DAdapter(Context context, ArrayList<Entity> entityList) {
        mContext = context;
        msList = new ArrayList<MstarItemInfo>();
        removeEntityList = new ArrayList<String>();
        myAppCount = 0;
        Cursor cursor = null;
        cursor = mContext.getContentResolver().query(Uri.parse(MSTAR_URI), null, null, null, null);

        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    MstarItemInfo item = new MstarItemInfo();
                    item.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                    item.setScene(cursor.getString(cursor.getColumnIndex(SCENE)));
                    item.setAgent(cursor.getString(cursor.getColumnIndex(AGENT)));
                    item.setObject(cursor.getString(cursor.getColumnIndex(OBJECT)));
                    item.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                    item.setPackageName(cursor.getString(cursor.getColumnIndex(PACKAGENAME)));
                    item.setClassName(cursor.getString(cursor.getColumnIndex(CLASSNAME)));
                    item.setLevel(cursor.getInt(cursor.getColumnIndex(LEVEL)));
                    item.setPage(cursor.getInt(cursor.getColumnIndex(PAGE)));
                    item.setIconIndex(cursor.getInt(cursor.getColumnIndex(ICONINDEX)));
                    if (item.getLevel() == 1) {
                        item.setObject(entityList.get(myAppCount).getName());
                        myAppCount++;
                    }
                    msList.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        pm = context.getPackageManager();
        getAllApps();

    }

    private void getAllApps() {
        final PackageManager packageManager = mContext.getPackageManager();
        final Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        listApps = packageManager.queryIntentActivities(mIntent, 0);
    }

    public int getRemoveListSize() {
        return removeEntityList.size();
    }

    private int getIndex(String objectName) {
        int index = -1;
        if (objectName == null) {
            return index;
        }
        for (int i = 0; i < msList.size(); i++) {
            if (msList.get(i).getObject().equals(objectName)) {
                for (int j = 0; j < listApps.size(); j++) {
                    if ((msList.get(i).getClassName()).equals(listApps.get(j).activityInfo.name)
                            && (msList.get(i).getPackageName())
                                    .equals(listApps.get(j).activityInfo.packageName)) {
                        index = j;
                    }
                }
            }
        }

        return index;
    }

    public String getRunApp(String objectName) {
        int idx = getIndex(objectName);
        if (idx == -1) {
            Log.i(TAG, "---->object is not exist");
            return null;
        }
        ResolveInfo appInfo = listApps.get(idx);
        return appInfo.activityInfo.packageName;
    }

    public void runApp(String objectName) {
        int idx = getIndex(objectName);
        if (idx == -1) {
            Log.i(TAG, "---->object is not exist");
            return;
        }
        ResolveInfo appInfo = listApps.get(idx);

        Log.i(TAG, "-----runApp--------" + appInfo.activityInfo.packageName);
        Intent mIntent = mContext.getPackageManager().getLaunchIntentForPackage(
                appInfo.activityInfo.packageName);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            mContext.startActivity(mIntent);

        } catch (ActivityNotFoundException anf) {
            Log.i(TAG, "-----runApp--------ActivityNotFoundException");
            Toast.makeText(mContext,
                    mContext.getResources().getString(R.string.package_not_find_ERROR_1),
                    Toast.LENGTH_SHORT).show();
        }

    }

    public Bitmap getIconBitmap(String objectName) {

        int idx = getIndex(objectName);
        if (idx == -1) {
            Log.i(TAG, "---->object is not exist");
            return null;
        }
        // get icon and title
        Drawable drawable = listApps.get(idx).loadIcon(pm);
        CharSequence appTitle = listApps.get(idx).loadLabel(pm);
        // init paint
        final Paint mPaint = new Paint();
        Typeface font = Typeface.create(Typeface.SERIF, Typeface.BOLD);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(22.5f);
        mPaint.setTypeface(font);
        // get background bitmap
        Bitmap icon_back = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.icon_back);

        // init canvas
        Bitmap bitmap = Bitmap.createBitmap(ICON_W, ICON_H, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawARGB(0x00, 0xff, 0xff, 0xff);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(icon_back, 0, 0, paint);

        drawable.setBounds(55, 15, canvas.getWidth() - 50, canvas.getHeight() - 90);
        drawable.draw(canvas);

        canvas.drawText(appTitle, 0, appTitle.length(),
                (canvas.getWidth() - getStringWidth(mPaint, (String) appTitle)) / 2,
                canvas.getHeight() - 45, mPaint);

        icon_back.recycle();
        Log.v(TAG, "=======drawable.getIntrinsicWidth()" + drawable.getIntrinsicWidth()
                + " + drawable.getIntrinsicWidth()==========" + drawable.getIntrinsicWidth());

        return bitmap;

    }

    public static Bitmap getReflectionBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(60, 60, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, 60, 60);
        drawable.draw(canvas);

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, 2 * bitmap.getHeight() / 3,
                bitmap.getWidth(), bitmap.getHeight() / 3, matrix, false);
        return reflectionImage;
    }

    public Bitmap getFavoriteBitmap(String objectName) {
        if (objectName == null) {
            return null;
        }
        int idx = getIndex(objectName);
        if (idx == -1 && !objectName.equalsIgnoreCase("Plane.003")) {
            Log.i(TAG, "---->object is not exist");
            return null;
        }
        // get icon and title
        Drawable drawable = null;
        String appTitle = null;
        if (objectName.equalsIgnoreCase("Plane.003")) {
            drawable = mContext.getResources().getDrawable(R.drawable.allapp00);
            appTitle = "AllApp";
        } else {
            drawable = listApps.get(idx).loadIcon(pm);
            appTitle = (String) listApps.get(idx).loadLabel(pm);
        }

        // init paint
        final Paint mPaint = new Paint();
        Typeface font = Typeface.create(Typeface.SERIF, Typeface.BOLD);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(11.25f);
        mPaint.setTypeface(font);

        // init canvas
        Bitmap bitmap = Bitmap.createBitmap(BITMAP_W, BITMAP_H, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawARGB(0x00, 0xff, 0xff, 0xff);
        drawable.setBounds(20, 5, BITMAP_W - 20, BITMAP_H - 30);
        drawable.draw(canvas);

        // Paint paint = new Paint();
        // Bitmap reflectionImage = getReflectionBitmap(drawable);
        // canvas.drawBitmap(reflectionImage, 20, 55, paint);
        //
        // Paint shaderPaint = new Paint();
        // LinearGradient shader = new LinearGradient(0,
        // reflectionImage.getHeight(), 0,
        // reflectionImage.getHeight(), 0x70ffffff, 0x00ffffff,
        // TileMode.MIRROR);
        //
        // shaderPaint.setShader(shader);
        //
        // shaderPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        //
        // canvas.drawRect(0, 85, BITMAP_W, (reflectionImage.getHeight()) * 3,
        // shaderPaint);

        canvas.drawText(appTitle, 0, appTitle.length(),
                (BITMAP_W - getStringWidth(mPaint, (String) appTitle)) / 2, BITMAP_H - 15, mPaint);

        // reflectionImage.recycle();
        Log.v(TAG,
                "***********" + drawable.getIntrinsicWidth() + "**********"
                        + drawable.getIntrinsicWidth());
        // saveBitmap(bitmap,objectName);
        return bitmap;

    }

    private void saveBitmap(Bitmap bitmap, String fileName) {

        // Log.v(TAG, "saveToBitmap");
        File f = new File(PATH_SDCARD + fileName + SUFIXX_PNG);
        try {
            f.createNewFile();
        } catch (IOException e) {
            Log.v(TAG, "-------->file create fail");
        }
        FileOutputStream fOut = null;

        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            Log.v(TAG, "FileNotFoundException in saveToBitmap");
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, MAX_QUALITY, fOut);

        try {
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            Log.v(TAG, "IOException in saveToBitmap");
            e.printStackTrace();
        }
    }

    public String getEntityName(String packageName) {
        String entity = null;
        for (int i = 0; i < msList.size(); i++) {
            if (packageName.equalsIgnoreCase(msList.get(i).getPackageName())) {
                entity = msList.get(i).getObject();
                break;
            }
        }
        return entity;
    }

    public String getAgentName(String packageName) {
        String agent = null;
        for (int i = 0; i < msList.size(); i++) {
            if (packageName.equalsIgnoreCase(msList.get(i).getPackageName())) {
                agent = msList.get(i).getAgent();
                break;
            }
        }
        return agent;
    }

    public int getEntityNum(Agent agent, String className) {
        int number = 0;
        if (className == null) {
            return -1;
        }
        for (int i = 0; i < msList.size(); i++) {
            if (agent.getAgentName().equalsIgnoreCase(msList.get(i).getAgent())) {
                if (msList.get(i).getClassName().equalsIgnoreCase(className)) {
                    return -1;
                } else {
                    number++;
                }
            }
        }

        return number;
    }

    public int getEntityNum(Agent agent) {
        int number = 0;
        for (int i = 0; i < msList.size(); i++) {
            if (agent.getAgentName().equalsIgnoreCase(msList.get(i).getAgent())) {
                number++;
            }
        }

        return number;
    }

    public boolean addIcon(String agentName, String entityName, String title, String packageName,
            String className) {

        if (agentName == null || entityName == null || title == null || packageName == null
                || className == null) {
            return false;
        }
        // write databass
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, msList.size() + 1);
        contentValues.put(SCENE, "MstarHomeUI");
        contentValues.put(AGENT, agentName);
        contentValues.put(OBJECT, entityName);
        contentValues.put(TITLE, title);

        contentValues.put(PACKAGENAME, packageName);
        contentValues.put(CLASSNAME, className);
        contentValues.put(LEVEL, 1);
        contentValues.put(PAGE, 0);
        contentValues.put(ICONINDEX, 0);
        Uri uri = mContext.getContentResolver().insert(Uri.parse(MSTAR_URI), contentValues);
        if (uri != null) {
            // add to msList
            MstarItemInfo additem = new MstarItemInfo();

            additem.setId((msList.size() + 1));
            additem.setScene("MstarHomeUI");
            additem.setAgent(agentName);
            additem.setObject(entityName);
            additem.setTitle(title);
            additem.setPackageName(packageName);
            additem.setClassName(className);
            additem.setLevel(1);
            additem.setPage(0);
            additem.setIconIndex(0);

            msList.add(additem);
            return true;
        }
        return false;
    }

    private int getEntityIndex(String entityName) {
        int index = -1;
        if (entityName == null) {
            return index;
        }
        for (int i = 0; i < msList.size(); i++) {
            if (entityName.equalsIgnoreCase(msList.get(i).getObject())) {
                index = i;
            }
        }
        return index;
    }

    public void getRemoveValus(ContentValues contentValues, MstarItemInfo itemInfo) {
        contentValues.put(SCENE, "MstarHomeUI");
        contentValues.put(AGENT, itemInfo.getAgent());

        contentValues.put(TITLE, itemInfo.getTitle());
        contentValues.put(PACKAGENAME, itemInfo.getPackageName());
        contentValues.put(CLASSNAME, itemInfo.getClassName());
        contentValues.put(LEVEL, itemInfo.getLevel());
        contentValues.put(PAGE, itemInfo.getPage());
        contentValues.put(ICONINDEX, itemInfo.getIconIndex());
    }

    public boolean removeIcon(String entityName, String agentName) {
        int index = getEntityIndex(entityName);
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        if (index == -1) {
            Log.i(TAG, "---->object is not exist");
            return false;
        }

        ContentValues contentValues = new ContentValues();
        for (int i = index + 1; i < msList.size(); i++) {
            MstarItemInfo removeItemInfo = new MstarItemInfo();
            removeItemInfo = (MstarItemInfo) msList.get(i).clone();
            getRemoveValus(contentValues, removeItemInfo);
            contentValues.put(OBJECT, msList.get(i - 1).getObject());
            ops.add(ContentProviderOperation.newUpdate(Uri.parse(MSTAR_URI))
                    .withSelection("_id=?", new String[] {
                        String.valueOf((removeItemInfo.getId() - 1))
                    }).withValues(contentValues).build());
            Log.i(TAG, i + "----------->id:" + (removeItemInfo.getId() - 1));
        }
        Log.i(TAG, "----------->list size:" + msList.size());
        ops.add(ContentProviderOperation.newDelete(Uri.parse(MSTAR_URI))
                .withSelection("_id=?", new String[] {
                    String.valueOf(msList.size())
                }).build());
        try {
            mContext.getContentResolver().applyBatch(MSTAR_AUTHORITY, ops);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // mContext.getContentResolver().delete(Uri.parse(MSTAR_URI), "_id=?",
        // new String[] { String.valueOf(msList.size()) });

        // msList.remove(index);
        int i = index;
        for (; i < msList.size() - 1; i++) {
            msList.get(i).setTitle(msList.get(i + 1).getTitle());
            msList.get(i).setClassName(msList.get(i + 1).getClassName());
            msList.get(i).setPackageName(msList.get(i + 1).getPackageName());
        }

        removeEntityList.add(msList.get(i).getObject());
        for (int j = 0; j < removeEntityList.size(); j++) {
            Log.i(TAG, "-------------->entity name:" + removeEntityList.get(j));
        }
        msList.remove(i);

        return true;
    }

    public void getValus(ContentValues contentValues, MstarItemInfo itemInfo) {
        contentValues.put(SCENE, "MstarHomeUI");
        contentValues.put(AGENT, itemInfo.getAgent());
        contentValues.put(TITLE, itemInfo.getTitle());
        contentValues.put(PACKAGENAME, itemInfo.getPackageName());
        contentValues.put(CLASSNAME, itemInfo.getClassName());
        contentValues.put(LEVEL, itemInfo.getLevel());
        contentValues.put(PAGE, itemInfo.getPage());
        contentValues.put(ICONINDEX, itemInfo.getIconIndex());
    }

    public void swapIcon(String thisEntity, String otherEntity) {
        Log.i(TAG, "------>" + thisEntity + " swap " + otherEntity);
        if (thisEntity == null || otherEntity == null) {
            return;
        }

        int thisindex = getEntityIndex(thisEntity);
        int otherindex = getEntityIndex(otherEntity);
        if (otherindex == -1 || thisindex == -1) {
            return;
        }
        MstarItemInfo thisItemInfo = new MstarItemInfo();
        thisItemInfo = (MstarItemInfo) msList.get(thisindex).clone();
        MstarItemInfo otherItemInfo = new MstarItemInfo();
        otherItemInfo = (MstarItemInfo) msList.get(otherindex).clone();

        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ContentValues contentValues = new ContentValues();
        getValus(contentValues, otherItemInfo);
        ops.add(ContentProviderOperation.newUpdate(Uri.parse(MSTAR_URI))
                .withSelection("object=?", new String[] {
                    thisItemInfo.getObject()
                }).withValues(contentValues).build());
        getValus(contentValues, thisItemInfo);
        ops.add(ContentProviderOperation.newUpdate(Uri.parse(MSTAR_URI))
                .withSelection("object=?", new String[] {
                    otherItemInfo.getObject()
                }).withValues(contentValues).build());
        try {
            mContext.getContentResolver().applyBatch(MSTAR_AUTHORITY, ops);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        msList.get(thisindex).setAgent(otherItemInfo.getAgent());
        msList.get(thisindex).setTitle(otherItemInfo.getTitle());
        msList.get(thisindex).setPackageName(otherItemInfo.getPackageName());
        msList.get(thisindex).setClassName(otherItemInfo.getClassName());
        msList.get(thisindex).setLevel(otherItemInfo.getLevel());
        msList.get(thisindex).setPage(otherItemInfo.getPage());
        msList.get(thisindex).setIconIndex(otherItemInfo.getIconIndex());

        msList.get(otherindex).setAgent(thisItemInfo.getAgent());
        msList.get(otherindex).setTitle(thisItemInfo.getTitle());
        msList.get(otherindex).setPackageName(thisItemInfo.getPackageName());
        msList.get(otherindex).setClassName(thisItemInfo.getClassName());
        msList.get(otherindex).setLevel(thisItemInfo.getLevel());
        msList.get(otherindex).setPage(thisItemInfo.getPage());
        msList.get(otherindex).setIconIndex(thisItemInfo.getIconIndex());

        Log.i(TAG, "------>" + thisEntity + " swap " + otherEntity + " success");
    }

    public void getSwapValus(ContentValues contentValues, MstarItemInfo itemInfo) {
        contentValues.put(SCENE, "MstarHomeUI");
        contentValues.put(OBJECT, itemInfo.getObject());
        contentValues.put(AGENT, itemInfo.getAgent());
        contentValues.put(TITLE, itemInfo.getTitle());
        contentValues.put(PACKAGENAME, itemInfo.getPackageName());
        contentValues.put(CLASSNAME, itemInfo.getClassName());
        contentValues.put(LEVEL, itemInfo.getLevel());
        contentValues.put(PAGE, itemInfo.getPage());
        contentValues.put(ICONINDEX, itemInfo.getIconIndex());
    }

    public void swapEntity(String fromEntity, String toEntity) {
        Log.i(TAG, "------>" + fromEntity + " swap " + toEntity);
        if (fromEntity == null || toEntity == null) {
            return;
        }

        int fromindex = getEntityIndex(fromEntity);
        int toindex = getEntityIndex(toEntity);
        if (fromindex == -1 || toindex == -1) {
            return;
        }
        MstarItemInfo fromItemInfo = new MstarItemInfo();
        fromItemInfo = (MstarItemInfo) msList.get(fromindex).clone();
        MstarItemInfo toItemInfo = new MstarItemInfo();
        toItemInfo = (MstarItemInfo) msList.get(toindex).clone();

        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ContentValues contentValues = new ContentValues();
        getSwapValus(contentValues, toItemInfo);
        ops.add(ContentProviderOperation.newUpdate(Uri.parse(MSTAR_URI))
                .withSelection("_id=?", new String[] {
                    String.valueOf(fromItemInfo.getId())
                }).withValues(contentValues).build());
        getSwapValus(contentValues, fromItemInfo);
        ops.add(ContentProviderOperation.newUpdate(Uri.parse(MSTAR_URI))
                .withSelection("_id=?", new String[] {
                    String.valueOf(toItemInfo.getId())
                }).withValues(contentValues).build());
        try {
            mContext.getContentResolver().applyBatch(MSTAR_AUTHORITY, ops);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        msList.get(fromindex).setAgent(toItemInfo.getAgent());
        msList.get(fromindex).setObject(toItemInfo.getObject());
        msList.get(fromindex).setTitle(toItemInfo.getTitle());
        msList.get(fromindex).setPackageName(toItemInfo.getPackageName());
        msList.get(fromindex).setClassName(toItemInfo.getClassName());
        msList.get(fromindex).setLevel(toItemInfo.getLevel());
        msList.get(fromindex).setPage(toItemInfo.getPage());
        msList.get(fromindex).setIconIndex(toItemInfo.getIconIndex());

        msList.get(toindex).setAgent(fromItemInfo.getAgent());
        msList.get(toindex).setObject(fromItemInfo.getObject());
        msList.get(toindex).setTitle(fromItemInfo.getTitle());
        msList.get(toindex).setPackageName(fromItemInfo.getPackageName());
        msList.get(toindex).setClassName(fromItemInfo.getClassName());
        msList.get(toindex).setLevel(fromItemInfo.getLevel());
        msList.get(toindex).setPage(fromItemInfo.getPage());
        msList.get(toindex).setIconIndex(fromItemInfo.getIconIndex());

        Log.i(TAG, "------>" + fromEntity + " swap " + toEntity + " success");
    }

    public String getRightEntity(String entityName) {
        String rightEntity = null;
        if (entityName == null) {
            return rightEntity;
        }
        int curIndex = getEntityIndex(entityName);
        if (curIndex == -1) {
            return rightEntity;
        }

        if ((curIndex + 1) < msList.size()) {
            rightEntity = msList.get(curIndex + 1).getObject();
        }

        return rightEntity;
    }

    public String getLeftEntity(String entityName, String agentName) {
        String leftEntity = null;
        if (entityName == null || agentName == null) {
            return leftEntity;
        }
        int curIndex = getEntityIndex(entityName);
        if (curIndex < 1) {
            return leftEntity;
        }
        if (agentName.equalsIgnoreCase(msList.get(curIndex - 1).getAgent())) {
            leftEntity = msList.get(curIndex - 1).getObject();
        }

        return leftEntity;
    }

    public void moveBack(MstarItemInfo fromItemInfo, MstarItemInfo toItemInfo, int fromIndex,
            int toIndex) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ContentValues contentValues = new ContentValues();
        for (int i = fromIndex + 1; i <= toIndex; i++) {
            MstarItemInfo moveItemInfo = new MstarItemInfo();
            moveItemInfo = (MstarItemInfo) msList.get(i).clone();
            getSwapValus(contentValues, moveItemInfo);
            ops.add(ContentProviderOperation.newUpdate(Uri.parse(MSTAR_URI))
                    .withSelection("_id=?", new String[] {
                        String.valueOf((moveItemInfo.getId() - 1))
                    }).withValues(contentValues).build());
            Log.i(TAG, "----->move back id:" + moveItemInfo.getId());
        }
        getSwapValus(contentValues, fromItemInfo);
        ops.add(ContentProviderOperation.newUpdate(Uri.parse(MSTAR_URI))
                .withSelection("_id=?", new String[] {
                    String.valueOf(toItemInfo.getId())
                }).withValues(contentValues).build());
        try {
            mContext.getContentResolver().applyBatch(MSTAR_AUTHORITY, ops);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        msList.remove(fromIndex);
        msList.add(toIndex, fromItemInfo);
        for (int i = fromIndex; i <= toIndex; i++) {
            msList.get(i).setId(i + 1);
        }

    }

    public void moveFront(MstarItemInfo fromItemInfo, MstarItemInfo toItemInfo, int fromIndex,
            int toIndex) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ContentValues contentValues = new ContentValues();
        for (int i = fromIndex - 1; i >= toIndex; i--) {
            MstarItemInfo moveItemInfo = new MstarItemInfo();
            moveItemInfo = (MstarItemInfo) msList.get(i).clone();
            getSwapValus(contentValues, moveItemInfo);
            ops.add(ContentProviderOperation.newUpdate(Uri.parse(MSTAR_URI))
                    .withSelection("_id=?", new String[] {
                        String.valueOf((moveItemInfo.getId() + 1))
                    }).withValues(contentValues).build());
            Log.i(TAG, "----->move front id:" + moveItemInfo.getId());
        }
        getSwapValus(contentValues, fromItemInfo);
        ops.add(ContentProviderOperation.newUpdate(Uri.parse(MSTAR_URI))
                .withSelection("_id=?", new String[] {
                    String.valueOf(toItemInfo.getId())
                }).withValues(contentValues).build());
        try {
            mContext.getContentResolver().applyBatch(MSTAR_AUTHORITY, ops);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // echoDB();
        msList.remove(fromIndex);
        msList.add(toIndex, fromItemInfo);
        for (int i = toIndex; i <= fromIndex; i++) {
            msList.get(i).setId(i + 1);
        }
        // echoMsList();
    }

    public void moveEntity(String fromEntity, String toEntity) {
        if (fromEntity == null || toEntity == null || fromEntity.equalsIgnoreCase(toEntity)) {
            return;
        }
        Log.i(TAG, "--->fromEntity: " + fromEntity + " --->toEntity: " + toEntity);
        int fromIndex = getEntityIndex(fromEntity);
        int toIndex = getEntityIndex(toEntity);
        if (fromIndex == -1 || toIndex == -1) {
            return;
        }

        MstarItemInfo fromItemInfo = new MstarItemInfo();
        fromItemInfo = (MstarItemInfo) msList.get(fromIndex).clone();
        MstarItemInfo toItemInfo = new MstarItemInfo();
        toItemInfo = (MstarItemInfo) msList.get(toIndex).clone();

        if (fromIndex < toIndex) {
            Log.i(TAG, "----->move from:" + fromIndex + "--to: " + toIndex);
            moveBack(fromItemInfo, toItemInfo, fromIndex, toIndex);
        } else {
            Log.i(TAG, "----->move from:" + fromIndex + "--to: " + toIndex);
            moveFront(fromItemInfo, toItemInfo, fromIndex, toIndex);
        }

    }

    public static int getStringWidth(Paint paint, String str) {
        int iRet = 0;
        if (paint == null) {
            return iRet;
        }

        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    public void echoDB() {

        Log.i(TAG, "-------echoDB------");
        Cursor c = mContext.getContentResolver()
                .query(Uri.parse(MSTAR_URI), null, null, null, null);
        if (c != null) {
            Log.v(TAG, "CURSOR SIZE " + c.getCount());
            while (c.moveToNext()) {
                Log.v(TAG,
                        "ID: " + c.getString(c.getColumnIndex(ID)) + " SCENE: "
                                + c.getString(c.getColumnIndex(SCENE)) + " AGENT: "
                                + c.getString(c.getColumnIndex(AGENT)) + " OBJECT: "
                                + c.getString(c.getColumnIndex(OBJECT)) + " TITLE :"
                                + c.getString(c.getColumnIndex(TITLE)) + " PAKCAGE: "
                                + c.getString(c.getColumnIndex(PACKAGENAME)) + " CLASSNAME: "
                                + c.getString(c.getColumnIndex(CLASSNAME)) + " LEVEL: "
                                + c.getInt(c.getColumnIndex(LEVEL)) + " PAGE: "
                                + c.getInt(c.getColumnIndex(PAGE)) + " INDEX: "
                                + c.getInt(c.getColumnIndex(ICONINDEX)));
            }
        }
        c.close();

    }

    public void echoMsList() {

        Log.i(TAG, "-------msList------");
        for (int i = 0; i < msList.size(); i++) {
            Log.v(TAG, "ID: " + msList.get(i).getId() + " SCENE: " + msList.get(i).getScene()
                    + " AGENT: " + msList.get(i).getAgent() + " OBJECT: "
                    + msList.get(i).getObject() + " TITLE :" + msList.get(i).getTitle()
                    + " PAKCAGE: " + msList.get(i).getPackageName() + " CLASSNAME: "
                    + msList.get(i).getClassName() + " LEVEL: " + msList.get(i).getLevel()
                    + " PAGE: " + msList.get(i).getPage() + " INDEX: "
                    + msList.get(i).getIconIndex());
        }
    }
}
