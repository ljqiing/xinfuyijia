
package com.android.mslauncher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.RemoteViews;

public class AppWidget3DHostView extends AppWidgetHostView {

    private final String TAG = "AppWidget3DHostView";

    // int index = 0;

    public AppWidget3DHostView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void updateAppWidget(RemoteViews remoteViews) {

        if (remoteViews != null) {
            if ("com.mstar.appwidget".equalsIgnoreCase(remoteViews.getPackage())) {
                final int width = super.getWidth();
                final int height = super.getHeight();
                Log.d("hostView", "updateAppWidget " + width + " " + height);
                if ((width > 0) && (height > 0)) {
                    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    canvas.drawARGB(0xff, 0xff, 0xff, 0xff);
                    super.draw(canvas);

                    ((Launcher3DActivity) Launcher3DActivity.mContext).setWidgetBitmap(bitmap);

                    // try {
                    // saveMyBitmap(bitmap, "test" + index);
                    // } catch (IOException e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                    // }
                    // index ++;
                }
            }
        }
        super.updateAppWidget(remoteViews);
    }

    public void saveMyBitmap(Bitmap mBitmap, String bitName) throws IOException {
        File f = new File("/mnt/usb/sda1/" + bitName + ".jpg");
        f.createNewFile();
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
