
package com.mstar.tvframework;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL11;

import com.android.mslauncher.MstarUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

public class Mstar3DUIActivity extends Activity implements OnLongClickListener {

    private static final String TAG = "MstarUIActivity";

    private static final int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

    private static final int EGL_RENDERABLE_TYPE = 0x3040;

    private static final int EGL_OPENGL_ES2_BIT = 0x0004;

    private static final int EGL_OPENGL_ES_BIT = 0x0001;

    private static final String travel_mode_name = "traveling-mode";

    private static final String travel_res_name = "traveling-res";

    private static final String travel_mem_format_name = "traveling-mem-format";

    private static final String travel_speed_name = "traveling-speed";

    // private TextView fpsText = null;
    protected String blendFile = new String();

    protected String configFile = new String();

    protected String agentFile = new String();

    protected Handler handler = null;

    protected EGL10 egl = null;

    protected GL11 gl = null;

    protected boolean ranInit = false;

    // protected boolean paused = false;
    protected EGLSurface eglSurface = null;

    protected EGLDisplay eglDisplay = null;

    protected EGLContext eglContext = null;

    protected EGLConfig eglConfig = null;

    protected Runnable initRunnable = null;

    // protected Runnable painter = null;

    // protected int surfaceWidth = 0;
    // protected int surfaceHeight = 0;

    protected FrameLayout widgetLinearLayout1 = null;

    protected FrameLayout widgetLinearLayout2 = null;

    protected SurfaceView surfaceView = null;

    protected MstarGLSurfaceView glView = null;

    protected long abstime = 0;

    protected boolean Isrender = false;

    // protected boolean bOnSurfaceCreated = false;

    // MstarOKitUI
    protected MstarOKitUI mActiveOKitUI = null;

    // protected ArrayList<MstarOKitUI> mOKitUIs;
    // TODO: How to get gameEngine
    private MGameEngine gameEngine = null;

    // //////////////////////////////////////////////////////////////////////////
    // Activity begin
    protected void Init3DEnv() {
        glView = new MstarGLSurfaceView(this);

        glView.setVisibility(View.VISIBLE);
        glView.setZOrderOnTop(true);
        glView.getHolder().setFormat(PixelFormat.TRANSPARENT);// PixelFormat.TRANSLUCENT
        glView.requestFocus();

        if (null == mActiveOKitUI) {
            mActiveOKitUI = MstarOKitUI.getInstance();
        }
        mActiveOKitUI.init(glView.getGameEngine());
        gameEngine = glView.gameEngine;
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        handler = new Handler();
        MstarUtil.getInstance().setActivity(this);
        Init3DEnv();
        glView.setOnLongClickListener(this);
        glView.setClickable(true);
        glView.setOnTouchListener(new glOnTouchLister());
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, String.format(" BaseUIActivity onResume ~~~~~~~~~~~\n"));
    }

    protected void onStop() {
        super.onStop();

        // handle the rest of events in queue.
        gameEngine.clearMsEventQueue();
    }

    protected void onPause() {
        Log.i(TAG, String.format(" BaseUIActivity onPause ~~~~~~~~~~~\n"));
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private class glOnTouchLister implements OnTouchListener {

        @Override
        public boolean onTouch(View arg0, MotionEvent event) {
            // TODO Auto-generated method stub
            // Log.i(TAG, "----------onTouch----------");
            // if(event.getAction() == MotionEvent.ACTION_DOWN){
            // Log.i(TAG, "----------ACTION_DOWN----------");
            // }
            // if(event.getAction() == MotionEvent.ACTION_UP){
            // Log.i(TAG, "----------ACTION_UP----------");
            // }
            // if (event.getAction() == MotionEvent.ACTION_MOVE){
            // Log.i(TAG, "----------ACTION_MOVE---------");
            // }
            int coordType = 1;
            // LEFT_BTN
            int btnType = 0;
            boolean ret = gameEngine.axisEvent(coordType, btnType, event.getAction(), event.getX(),
                    event.getY(), event);
            if (!ret) {
                ret = arg0.onTouchEvent(event);
            }
            return false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "----------onTouchEvent----------");
        boolean ret = super.onTouchEvent(event);
        if (!ret) {
            int coordType = 1;
            // LEFT_BTN
            int btnType = 0;
            ret = gameEngine.axisEvent(coordType, btnType, event.getAction(), event.getX(),
                    event.getY(), event);
        }
        return ret;
    }

    public boolean onGenericMotionEvent(MotionEvent event) {

        Log.v(TAG, "=== onGenericMotionEvent");
        if ((event.getSource() & InputDevice.SOURCE_MOUSE) != 0) {
            int coordType = 1;
            // LEFT_BTN
            int btnType = 0;

            switch (event.getAction()) {
                case MotionEvent.ACTION_HOVER_MOVE:
                    // process the mouse hover movement...
                    // Log.v(TAG,"=== MotionEvent.ACTION_HOVER_MOVE");
                    gameEngine.axisEvent(coordType, btnType, MotionEvent.ACTION_HOVER_MOVE,
                            event.getX(), event.getY(), event);
                    return true;

            }
        }

        return super.onGenericMotionEvent(event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        boolean ret = gameEngine.keyEvent(event.getAction(), event.getUnicodeChar(),
                event.getKeyCode(), event);

        if (!ret) {
            ret = super.onKeyDown(keyCode, event);
        }
        return ret;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {

        boolean ret = gameEngine.keyEvent(event.getAction(), event.getUnicodeChar(),
                event.getKeyCode(), event);
        if (!ret)
            ret = super.onKeyUp(keyCode, event);
        return ret;
    }

    @Override
    public boolean onLongClick(View arg0) {
        // TODO Auto-generated method stub
        Log.i(TAG, "----------onLongClick----------");
        gameEngine.setLongClickFlag();
        return false;
    }

    protected void initMainView() {
    }

    protected void runTvApp() {
        Intent intent = new Intent("mstar.tvsetting.ui.intent.action.RootActivity");
        // startActivity(intent);
    }

    public void callBackInitDone(String sceneName) {
        Log.v(TAG, "++++++++++++++++++++++++Mstar3DUIActivity:callBackInitDone invoked");

    }
}
