
package com.mstar.tvframework;

import java.util.ArrayList;

import com.android.mslauncher.MstarUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MGameEngine {
    static {
        System.loadLibrary("ogrekit_ui_jni2");
        preloadBlendFile("/data/data/com.android.mlauncher/files/MStarAnHome.blend");
    }

    public static final String TAG = "MGameEngine";

    public ArrayList<Runnable> mMsEventQueue = new ArrayList<Runnable>();

    public boolean m_bRet = false;

    public String m_strRet;

    public int m_iRet;

    private static MGameEngine instance = new MGameEngine();

    private MGameEngine() {
        m_bRet = false;
        int m_iRet = 0;
    }

    public static MGameEngine getInstance() {
        return instance;
    }

    public native boolean init_native(String blendFile, String configFile, String EtcPath);

    public native boolean cleanup_native();

    public native static boolean preloadBlendFile_native(String blendFile);

    public native boolean keyEvent_native(int action, int unicodeChar, int keyCode, KeyEvent event);

    public native boolean render_native(int drawWidth, int drawHeight, boolean forceRedraw);

    public native boolean axisEvent_native(int coordType, int btnType, int action, float x,
            float y, MotionEvent event);

    public native boolean runLuaScript_native(String Script);

    public native boolean setImage_native(Bitmap image, String appName, String sceneName,
            String objName, int subEntityID, int techID, int passID, int stateID);

    public native boolean setImage_native(Bitmap image, String appName, String sceneName,
            String objName, int subEntityID, int techID, int passID, int stateID, boolean formatFlag);

    public native String queryCurrentScene_native();

    public native void createDynamicTexture_native(int width, int height, int num_mips,
            String pixelFormat, String name, String group, int texType, int usage,
            boolean hwGammaCorrection, int fsaa, int fsaaHint);

    public native void delDynamicTexture_native();

    public native boolean replacewithDynamicTex_native(String appName, String sceneName,
            String objName, int subEntityID, int techID, int passID, int stateID);

    public native void enableUpdateDynamicTex_native(boolean bFlag);

    public native void startVECapture_native();

    public native void endVECapture_native();

    public native int queryVECaptureAttr_native(String attribute);

    public native void setVECaptureAttr_native(String attribute, int value);

    public native void setMouseLongClick_native();

    public void callBackInitDone(String sceneName) {
        Log.v(TAG, "+++++++++++++MGameEngine:callBackInitDone!!");

        Activity activity = MstarUtil.getInstance().getActivity();
        if (activity instanceof Mstar3DUIActivity) {
            Mstar3DUIActivity activity_3DUI = (Mstar3DUIActivity) activity;
            activity_3DUI.callBackInitDone(sceneName);
        }

    }

    public void callBackSendEvent(MGameEvent event) {
        Log.v(TAG, "+++++++++MGameEngine:callBackSendEvent!!!!!!!");
        MstarOKitUI.getInstance().dispatchEvent(event);
    }

    // end of define JNIs

    // JNI wrapper, make JNI calls be serialized.
    public boolean init(String blendFile, String configFile, String EtcPath) {
        return init_native(blendFile, configFile, EtcPath);
    }

    public boolean cleanup() {
        return cleanup_native();
    }

    public boolean keyEvent(int action, int unicodeChar, int keyCode, KeyEvent event) {
        final int actionL = action;
        final int unicodeCharL = unicodeChar;
        final int keyCodeL = keyCode;
        final KeyEvent eventL = event;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    Log.v(TAG, "keyEvent unicodeChar = " + unicodeCharL + ", keyCode = " + keyCodeL
                            + "!! ");
                    m_bRet = keyEvent_native(actionL, unicodeCharL, keyCodeL, eventL);
                }
            });
        }
        return m_bRet;
    }

    public boolean render(int drawWidth, int drawHeight, boolean forceRedraw) {
        try {

            Runnable eventR = null;

            while (true) {
                synchronized (this) {

                    if (!mMsEventQueue.isEmpty()) {
                        eventR = mMsEventQueue.remove(0);
                    }
                }
                if (eventR == null)
                    break;

                eventR.run();
                eventR = null;
            }

            return render_native(drawWidth, drawHeight, true);

        } finally {
        }
    }

    public boolean axisEvent(int coordType, int btnType, int action, float x, float y,
            MotionEvent event) {
        final int coordTypeL = coordType;
        final int btnTypeL = btnType;
        final int actionL = action;
        final float xL = x;
        final float yL = y;
        final MotionEvent eventL = event;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    m_bRet = axisEvent_native(coordTypeL, btnTypeL, actionL, xL, yL, eventL);
                }
            });
        }
        return m_bRet;
    }

    public boolean runLuaScript(String Script) {
        final String ScriptL = Script;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    m_bRet = runLuaScript_native(ScriptL);
                }
            });
        }
        return m_bRet;
    }

    public boolean setImage(Bitmap image, String appName, String sceneName, String objName,
            int subEntityID, int techID, int passID, int stateID) {
        final Bitmap imageL = image;
        final String appNameL = appName;
        final String sceneNameL = sceneName;
        final String objNameL = objName;
        final int subEntityIDL = subEntityID;
        final int techIDL = techID;
        final int passIDL = passID;
        final int stateIDL = stateID;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    m_bRet = setImage_native(imageL, appNameL, sceneNameL, objNameL, subEntityIDL,
                            techIDL, passIDL, stateIDL);
                }
            });
        }
        return m_bRet;
    }

    public boolean setImage(Bitmap image, String appName, String sceneName, String objName,
            int subEntityID, int techID, int passID, int stateID, boolean formatFlag) {
        final Bitmap imageL = image;
        final String appNameL = appName;
        final String sceneNameL = sceneName;
        final String objNameL = objName;
        final int subEntityIDL = subEntityID;
        final int techIDL = techID;
        final int passIDL = passID;
        final int stateIDL = stateID;
        final boolean formatFlagL = formatFlag;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    m_bRet = setImage_native(imageL, appNameL, sceneNameL, objNameL, subEntityIDL,
                            techIDL, passIDL, stateIDL, formatFlagL);
                }
            });
        }
        return m_bRet;
    }

    public String queryCurrentScene() {
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    m_strRet = queryCurrentScene_native();
                }
            });
        }
        return m_strRet;
    }

    public void createDynamicTexture(int width, int height, int num_mips, String pixelFormat,
            String name, String group, int texType, int usage, boolean hwGammaCorrection, int fsaa,
            int fsaaHint) {
        final int widthL = width;
        final int heightL = height;
        final int num_mipsL = num_mips;
        final String pixelFormatL = pixelFormat;
        final String nameL = name;
        final String groupL = group;
        final int texTypeL = texType;
        final int usageL = usage;
        final boolean hwGammaCorrectionL = hwGammaCorrection;
        final int fsaaL = fsaa;
        final int fsaaHintL = fsaaHint;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    createDynamicTexture_native(widthL, heightL, num_mipsL, pixelFormatL, nameL,
                            groupL, texTypeL, usageL, hwGammaCorrectionL, fsaaL, fsaaHintL);
                }
            });
        }
    }

    public void delDynamicTexture() {
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    delDynamicTexture_native();
                }
            });
        }
    }

    public void enableUpdateDynamicTex(boolean bFlag) {
        final boolean bFlagL = bFlag;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    enableUpdateDynamicTex_native(bFlagL);
                }
            });
        }
    }

    public boolean replacewithDynamicTex(String appName, String sceneName, String objName,
            int subEntityID, int techID, int passID, int stateID) {
        final String appNameL = appName;
        final String sceneNameL = sceneName;
        final String objNameL = objName;
        final int subEntityIDL = subEntityID;
        final int techIDL = techID;
        final int passIDL = passID;
        final int stateIDL = stateID;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    replacewithDynamicTex_native(appNameL, sceneNameL, objNameL, subEntityIDL,
                            techIDL, passIDL, stateIDL);
                }
            });
        }
        return m_bRet;
    }

    public int queryVECaptureAttr(String attribute) {
        final String attributeL = attribute;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    m_iRet = queryVECaptureAttr_native(attributeL);
                }
            });
        }
        return m_iRet;
    }

    public void setVECaptureAttr(String attribute, int value) {
        final String attributeL = attribute;
        final int valueL = value;
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    setVECaptureAttr_native(attributeL, valueL);
                }
            });
        }
    }

    public void startVECapture() {
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    startVECapture_native();
                }
            });
        }
    }

    public void endVECapture() {
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    endVECapture_native();
                }
            });
        }
    }

    public void setLongClickFlag() {
        synchronized (this) {
            mMsEventQueue.add(new Runnable() {
                public void run() {
                    setMouseLongClick_native();
                }
            });
        }
    }

    public static boolean preloadBlendFile(String blendFile) {
        return preloadBlendFile_native(blendFile);
    }

    // end define JNI wrappers

    public void clearMsEventQueue() {
        Runnable eventR = null;
        while (true) {
            synchronized (this) {

                if (!mMsEventQueue.isEmpty()) {
                    mMsEventQueue.remove(0);
                }
            }
            if (eventR == null)
                break;

            eventR.run();
            eventR = null;
        }
    }
}
