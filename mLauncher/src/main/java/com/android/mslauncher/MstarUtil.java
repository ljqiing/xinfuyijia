
package com.android.mslauncher;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.HashMap;

//import com.mstar.babaoframework.AppWidgetInfo;
//import com.mstar.babaoframework.ItemInfo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;

public class MstarUtil {
    // file suffix, .PNG
    private static final String SUFIXX_PNG = ".png";

    // for storage
    private static final String PATH_SDCARD = "/sdcard/";

    // log tag
    private final static String TAG = "MstarUtil";

    // max quality
    private static final int MAX_QUALITY = 100;

    // singleton instance
    private static MstarUtil instance = new MstarUtil();

    private HashMap<String, String> mAppLocalValues;

    private Activity mActivity = null;

    private static final int FONT_BITMAP_WIDTH = 100;

    private static final int FONT_BITMAP_HEIGHT = 100;

    private MstarUtil() {
        mAppLocalValues = new HashMap<String, String>();
        mAppLocalValues.put("STORAGE_ROOT", Environment.getExternalStorageDirectory()
                .getAbsolutePath());
    }

    /**
     * @param activity current focus UI.
     */
    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * @return activity name.
     */
    public Activity getActivity() {
        return this.mActivity;
    }

    /**
     * @return singleton instance.
     */
    public static MstarUtil getInstance() {
        // Log.v(TAG, "getInstance");
        return instance;
    }

    /**
     * Checks whether a key is in the app local value list.
     *
     * @param key The key to test.
     * @return Whether or not the key is in the app local value list.
     */
    public boolean hasAppLocalValue(String key) {
        // Log.v(TAG, "hasAppLocalValue");
        return mAppLocalValues.containsKey(key);
    }

    /**
     * Get the specified key value from the app local value list.
     *
     * @param key The key to get the value of.
     * @return The key's value.
     */
    public String getAppLocalValue(String key) {
        // Log.v(TAG, "getAppLocalValue");
        return mAppLocalValues.get(key);
    }

    /**
     * Set the specified key value in the app local value list.
     *
     * @param key The key to set the value of
     * @param value The value
     */
    public void setAppLocalValue(String key, String value) {
        // Log.v(TAG, "setAppLocalValue");
        mAppLocalValues.put(key, value);
    }

    /**
     * This function is used to get the parameters used to start the Activity
     * via, for example:
     *
     * <pre>
     * adb shell am start -a android.intent.action.MAIN -n com.nvidia.devtech.water/com.nvidia.devtech.water.Water -e param1 1 -e param2 2
     * </pre>
     *
     * Where "param1" and "param2" are the parameter names and "1" and "2" are
     * the parameter values.
     *
     * @param paramName The name of the parameter to get.
     * @return The parameter
     */
    public String getParameter(String paramName) {
        // Log.v(TAG, "getParameter");
        return mActivity.getIntent().getStringExtra(paramName);
    }

    /**
     * @param keyCode button or keyboard code.
     * @param event Contains constants for key events, see {@link KeyEvent}.
     * @param keyStatus press down or up.
     */
    public void printKeyCode(int keyCode, KeyEvent event, int keyStatus) {
        // Log.v(TAG, "printKeyCode");
        String status = "Key Down";
        if (keyStatus == KeyEvent.ACTION_UP) {
            status = "Key Up";
        }
        String strkey = String.format("%s keyCode: %d", status, event.getKeyCode());
        Log.v(TAG, strkey);
    }

    /**
     * @param bitmap bitmap that write to file.
     * @param fileName name of file.
     * @throws IOException file operation exception.
     */
    public void saveToBitmap(Bitmap bitmap, String fileName) throws IOException {

        // Log.v(TAG, "saveToBitmap");
        File f = new File(PATH_SDCARD + fileName + SUFIXX_PNG);
        f.createNewFile();
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

    public boolean isExist(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    // @Deprecated
    // private void SaveToFile(ArrayList<ItemInfo> list, String filePath) {
    // String str = "";
    // for (ItemInfo cell : list) {
    // str = str + cell.getClassName() + "\n";
    // }
    // WriteFile(filePath, str);
    // }
    //
    // /**
    // *
    // * @param fileName
    // * @param content
    // */
    // @Deprecated
    // private static void WriteFile(String fileName, String content) {
    // try {
    // FileWriter writer = new FileWriter(fileName, false);
    // writer.write(content);
    // writer.flush();
    // writer.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public Bitmap getFontBmp(Context context, int srcId) {
        Bitmap mBitmap = Bitmap.createBitmap(FONT_BITMAP_WIDTH, FONT_BITMAP_HEIGHT,
                Config.ARGB_8888);

        Canvas canvas = new Canvas(mBitmap);
        canvas.drawARGB(0, 0, 0, 0);

        String familyName = "";
        Typeface font = Typeface.create(familyName, Typeface.ITALIC);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(15);
        // paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        // paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(font);

        canvas.drawText(context.getResources().getString(srcId), 12, 95, paint);

        return mBitmap;
    }

    public Bitmap getFontBmp(Context context, int srcId, int position) {
        Bitmap mBitmap = Bitmap.createBitmap(FONT_BITMAP_WIDTH, FONT_BITMAP_HEIGHT,
                Config.ARGB_8888);

        Canvas canvas = new Canvas(mBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        String familyName = "";
        Typeface font = Typeface.create(familyName, Typeface.ITALIC);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(15);
        // paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        // paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(font);

        canvas.drawText(context.getResources().getString(srcId), position, 95, paint);

        return mBitmap;
    }

    public void getFontImage(Bitmap bitmap, String fontName) {
        File captureFile = new File("/sdcard/" + fontName + ".png");
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(captureFile));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
