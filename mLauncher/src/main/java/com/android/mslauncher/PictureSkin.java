
package com.android.mslauncher;

import android.os.IBinder;
import android.os.ServiceManager;
import android.os.RemoteException;
import android.os.Parcel;

public class PictureSkin {
    private IBinder surfaceFlinger = null;

    private boolean connected = false;

    private static PictureSkin mPictureSkin = null;

    private PictureSkin() {
        surfaceFlinger = null;
        connected = false;
    }

    public static PictureSkin getInstance() {
        if (mPictureSkin != null) {
            return mPictureSkin;
        } else {
            return new PictureSkin();
        }
    }

    public void Connect() {
        if (connected == false) {
            surfaceFlinger = ServiceManager.getService("SurfaceFlinger");
            if (surfaceFlinger != null) {
                connected = true;
            } else {
                connected = false;
            }
        }

    }

    public void setSurfaceResolutionMode(int width, int height, int hstart, int interleave,
            int orientation, long value) {

        try {
            if (surfaceFlinger != null) {
                Parcel data = Parcel.obtain();
                data.writeInterfaceToken("android.ui.ISurfaceComposer");
                data.writeInt(width);
                data.writeInt(height);
                data.writeInt(hstart);
                data.writeInt(interleave);
                data.writeInt(orientation);
                data.writeLong(value);
                surfaceFlinger.transact(IBinder.FIRST_CALL_TRANSACTION + 17, data, null, 0);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}
