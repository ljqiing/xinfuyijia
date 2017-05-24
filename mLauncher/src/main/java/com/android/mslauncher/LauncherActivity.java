//<MStar Software>
//******************************************************************************
// MStar Software
// Copyright (c) 2010 - 2012 MStar Semiconductor, Inc. All rights reserved.
// All software, firmware and related documentation herein ("MStar Software") are
// intellectual property of MStar Semiconductor, Inc. ("MStar") and protected by
// law, including, but not limited to, copyright law and international treaties.
// Any use, modification, reproduction, retransmission, or republication of all
// or part of MStar Software is expressly prohibited, unless prior written
// permission has been granted by MStar.
//
// By accessing, browsing and/or using MStar Software, you acknowledge that you
// have read, understood, and agree, to be bound by below terms ("Terms") and to
// comply with all applicable laws and regulations:
//
// 1. MStar shall retain any and all right, ownership and interest to MStar
//    Software and any modification/derivatives thereof.
//    No right, ownership, or interest to MStar Software and any
//    modification/derivatives thereof is transferred to you under Terms.
//
// 2. You understand that MStar Software might include, incorporate or be
//    supplied together with third party's software and the use of MStar
//    Software may require additional licenses from third parties.
//    Therefore, you hereby agree it is your sole responsibility to separately
//    obtain any and all third party right and license necessary for your use of
//    such third party's software.
//
// 3. MStar Software and any modification/derivatives thereof shall be deemed as
//    MStar's confidential information and you agree to keep MStar's
//    confidential information in strictest confidence and not disclose to any
//    third party.
//
// 4. MStar Software is provided on an "AS IS" basis without warranties of any
//    kind. Any warranties are hereby expressly disclaimed by MStar, including
//    without limitation, any warranties of merchantability, non-infringement of
//    intellectual property rights, fitness for a particular purpose, error free
//    and in conformity with any international standard.  You agree to waive any
//    claim against MStar for any loss, damage, cost or expense that you may
//    incur related to your use of MStar Software.
//    In no event shall MStar be liable for any direct, indirect, incidental or
//    consequential damages, including without limitation, lost of profit or
//    revenues, lost or damage of data, and unauthorized system use.
//    You agree that this Section 4 shall still apply without being affected
//    even if MStar Software has been modified by MStar in accordance with your
//    request or instruction for your use, except otherwise agreed by both
//    parties in writing.
//
// 5. If requested, MStar may from time to time provide technical supports or
//    services in relation with MStar Software to you for your use of
//    MStar Software in conjunction with your or your customer's product
//    ("Services").
//    You understand and agree that, except otherwise agreed by both parties in
//    writing, Services are provided on an "AS IS" basis and the warranty
//    disclaimer set forth in Section 4 above shall apply.
//
// 6. Nothing contained herein shall be construed as by implication, estoppels
//    or otherwise:
//    (a) conferring any license or right to use MStar name, trademark, service
//        mark, symbol or any other identification;
//    (b) obligating MStar or any of its affiliates to furnish any person,
//        including without limitation, you and your customers, any assistance
//        of any kind whatsoever, or any information; or
//    (c) conferring any license or right under any intellectual property right.
//
// 7. These terms shall be governed by and construed in accordance with the laws
//    of Taiwan, R.O.C., excluding its conflict of law rules.
//    Any and all dispute arising out hereof or related hereto shall be finally
//    settled by arbitration referred to the Chinese Arbitration Association,
//    Taipei in accordance with the ROC Arbitration Law and the Arbitration
//    Rules of the Association by three (3) arbitrators appointed in accordance
//    with the said Rules.
//    The place of arbitration shall be in Taipei, Taiwan and the language shall
//    be English.
//    The arbitration award shall be final and binding to both parties.
//
//******************************************************************************
//<MStar Software>

package com.android.mslauncher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import android.app.ProgressDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.IWindowManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import com.mstar.android.MKeyEvent;
import com.mstar.android.tv.TvChannelManager;
import com.mstar.android.tv.TvCommonManager;
import com.mstar.android.tv.TvPictureManager;
import com.mstar.android.tv.TvPipPopManager;
import com.mstar.android.tvapi.common.PictureManager;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.exception.TvCommonException;
import com.mstar.android.tvapi.common.vo.Enum3dType;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceInputType;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceType;
import com.mstar.android.tvapi.common.vo.EnumPipModes;
import com.mstar.android.tvapi.common.vo.EnumScalerWindow;
import com.mstar.android.tvapi.common.vo.PanelProperty;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.android.tvapi.common.vo.VideoWindowType;
import com.mstar.launcher.viewpager.MstarViewPager;
import com.mstar.launcher.viewpager.MstarViewPager.OnPageChangeListener;
import com.mstar.launcher.viewpager.PagerAdapter;
import android.hardware.display.DisplayManager;
import android.os.SystemProperties;

/////////////////////////////////////////////
public class LauncherActivity extends Activity {

    private static final String TAG = "LauncherActivity";

    private static final String STR_STATUS_NONE = "0";

    private static final String STR_STATUS_SUSPENDING = "1";

    private static final int SLEEP_TIME = 50;

    private static final int RETRY_COUNT = 40; // retry for 2s

    private ProgressDialog waitDialog;

    private static boolean isCheckSNOKTheadRunning = false;

    private static final int DISMISS_WAITDIALOG = 0;

    private static final int GO_TO_SETTING = 1;

    private static final int GO_TO_TVAPK = 2;

    private static final int GO_TO_MM = 3;

    private static final int SET_WIN_SCALE = 4;

    private  boolean isPause = false;

    final float W_1920 = 1920f;

    final float H_1080 = 1080f;

    private List<View> mListView;

    private MstarViewPager mViewPager;

    private List<ResolveInfo> listApps;

    LayoutInflater mInflater;

    LinearLayout mcontent;

    private View view1;

    private View view2;

    private View view3;

    private GridView mGridView1;

    private GridView mGridView2;

    private GridView mGridView3;

    private List<ImageView> idxImageViews;

    private ImageView pageidx1;

    private ImageView pageidx2;

    private ImageView pageidx3;

    public static Context msContext;

    public GridPageAdapter mGridPageAdapter1;

    public GridPageAdapter mGridPageAdapter2;

    public GridPageAdapter mGridPageAdapter3;

    public static final int PAGE_APP_NUM = 15;

    private int itemidxpage2 = -1;

    private int itemidx = -1;

    private int currentpageidx = 1;

    private static final int PAGE1 = 0;

    private static final int PAGE2 = 1;

    private static final int PAGE3 = 2;

    private static final int PAGE_IDLE = 0;

    private static final int PAGE_SLIP = 1;

    private static final int PAGE_SCROLL = 1;

    private static final int PAGE_READYTOSCROLL = 2;

    private Button buttonAllapp;

    private Button buttonAppstore;

    private Button buttonBrowser;

    private Button buttonSetting;

    private Button buttonTV;

    private Button buttonMM;

    private TextView buttonzixun;

    private TextView buttontianqi;

    private Button buttonxinxi;
    //ljq add
    private Button buttonyinyong;

    private TextView textViewTime;

    private boolean isgetTime = true;

    private static final int msgKey1 = 1;

    private ShowTimeHandler showTimeHandler;

    private ShowTimeThread showTimeThread = new ShowTimeThread();

    //ljq add end


    private Button imageTV;

    private IconAnimationHandler iconAnimationHandler;

    private LoadIconThread loadIconThread = new LoadIconThread();

    private static final int ICON_FRAME_NUM = 35;

    private static final int FRAME_DURATION = 80; // ms

    ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();

    private Button itemButton = null;

    private Boolean bLoadIcon = false;

    private Boolean bLoadBreak = false;

    private Boolean bAnimationPause = false;

    private Boolean bExitThread = false;

    private Boolean bSystemShutdown = false;

    // ////////////////for tv///////////////////////////
    SurfaceView surfaceView = null;

    android.view.SurfaceHolder.Callback callback;

    // private WindowManager wm;
    // LayoutParams surfaceParams;
    private boolean createsurface = false;

    private EnumInputSource toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_NONE;

    private InputSourceThread inputSourceThread = new InputSourceThread();

    private Boolean bSync = true;

    private static final int SCALE_NONE = 0;

    private static final int SCALE_FULL = 1;

    private static final int SCALE_SMALL = 2;

    private int fullScale = SCALE_NONE;

    private static final String ThirdPartyDtvEnv = "thirdpartydtv"; // colin@20130418
                                                                    // add for
                                                                    // ddi

    private int ThirdPartyDtvValue = 0;// colin@20130418 add for ddi

    // ////////////////for tv end///////////////////////////
    // //////////////////for wallpaper///////////////////////////////
    private static boolean mWallpaperChecked;

    private final BroadcastReceiver mWallpaperReceiver = new WallpaperIntentReceiver();

    // ////////////////////////////////////////////////////////////
    // /////////////////////anim//////////////////////////////////
    private Animation scale;

    // ///////////////////////////////////////////////////////////
    private int focusIdx;

    private static final int TOTALICON = 15;

    // ////////////appWidget////////////////
    private AppWidgetHost mAppWidgetHost;

    private AppWidgetManager mAppWidgetManager;

    private static final int APPWIDGET_HOST_ID = 1024;

    private int appWidgetId1;

    private int appWidgetId2;

    private int appWidgetId3;

    private AppWidgetProviderInfo appWidgetInfo1 = null;

    private AppWidgetProviderInfo appWidgetInfo2 = null;

    private AppWidgetProviderInfo appWidgetInfo3 = null;

    private AbsoluteLayout.LayoutParams ablp1;

    private AbsoluteLayout.LayoutParams ablp2;

    private AbsoluteLayout.LayoutParams ablp3;

    private AbsoluteLayout absoluteLayout;

    AppWidgetHostView hostView1;

    AppWidgetHostView hostView2;

    AppWidgetHostView hostView3;

    private int continuousLoadIcon = 0;

    // control status bar hide and show
    private boolean statusBarIsVisible = false;

    private static final int HIDE_STATUS_BAR = 0;

    public Handler myHandler = new StatusBarHandler();

    private boolean mixKey = true;

    private boolean hasTVClick = false;

    private List<Button> dockBtns = null;

    private Delay2LaunchAppWhenSupernovaNotOkHandler delay2LaunchAppWhenSupernovaNotOkHandler;

    private final String FILE_FLY_LAUNCH = "com.jrm.filefly.action";

    private final int E_PIP_MODE_PIP = 0;

    private final int E_PIP_MODE_POP = 1;

    private final CharSequence[] laucherInputsources = {
            "TV", "Storage"
    };

    private final String IS_POWER_ON_PROPERTY = "mstar.launcher.1stinit";

    private boolean isWidgetFocus = false;
    private boolean isMainItemsFocus = false;

    //ljq add
    public UpdateUIBroadcastReceiver updateUIBroadcastReceiver;
    public wifiBrocasRceiver wifiBrocasRceiver;
    //ljq add end


    class StatusBarHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HIDE_STATUS_BAR)
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);

        }
    };

    private ProgressDialog getWaitDialog() {
        if (waitDialog == null) {
            waitDialog = new ProgressDialog(this);
        }
        return waitDialog;
    }

    public OnTouchListener statusBarShowListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d(TAG, "---MotionEvent.ACTION_DOWN---");
                    if (statusBarIsVisible == false) {
                        Log.d(TAG, "---set statusBarIsVisible true---");
                        statusBarIsVisible = true;
                        getWindow().getDecorView().setSystemUiVisibility(
                                View.SYSTEM_UI_FLAG_VISIBLE);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                statusBarIsVisible = false;
                                Message message = new Message();
                                message.what = HIDE_STATUS_BAR;
                                myHandler.sendMessage(message);
                            }
                        }, 4000);

                    }
                    break;
            }
            return false;
        }
    };

    class IconAnimationHandler extends Handler {

        public IconAnimationHandler() {
        }

        public IconAnimationHandler(Looper l) {
            super(l);
        }

        // //////////////////////////////
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b = msg.getData();
            int index = b.getInt("index");
            Bitmap bmp = null;
            if (bitmapList.size() > index) {
                bmp = bitmapList.get(index);
            }
            if ((bmp != null) && (!bLoadBreak)) {
                BitmapDrawable bd = new BitmapDrawable(getResources(), bmp);
                itemButton.setBackgroundDrawable(bd);
            }
        }
    }

    class LoadIconThread implements Runnable {
        boolean suspendFlag = false;

        void mySuspend() {
            suspendFlag = true;
        }

        synchronized void myResume() {
            suspendFlag = false;
            notify();
        }

        @Override
        public void run() {
            Boolean bLoadComplete = false;
            long startTime = 0;
            long endTime = 0;
            while (!bExitThread) {
                continuousLoadIcon = 0;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (continuousLoadIcon == 0 && bLoadIcon) {
                    synchronized (bLoadIcon) {
                        bLoadIcon = false;
                        bLoadBreak = false;
                    }
                    int index = 0;
                    int sendIndex = 0;
                    while (!bLoadBreak) {
                        if (!bLoadComplete) {
                            // Log.v(TAG,
                            // "---------***-------- not bloadcomplete  " +
                            // bLoadComplete);
                            int id = 0;
                            if (itemButton == buttonAllapp) {
                                id = R.drawable.allapp01 + index;
                            } else if (itemButton == buttonAppstore) {
                                id = R.drawable.appstore01 + index;
                            } else if (itemButton == buttonBrowser) {
                                id = R.drawable.webbrowser01 + index;
                            } else if (itemButton == buttonSetting) {
                                id = R.drawable.setting01 + index;
                            } else if (itemButton == buttonTV) {
                                id = R.drawable.inputsource01 + index;
                            } else if (itemButton == buttonMM) {
                                id = R.drawable.localmm01 + index;
                            } else {
                                break;
                            }
                            Bitmap bmp = BitmapFactory.decodeResource(getResources(), id);
                            if (bmp != null) {
                                bitmapList.add(bmp);
                                if (index < ICON_FRAME_NUM - 1) {
                                    index++;
                                } else {
                                    bLoadComplete = true;
                                }
                            }
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        endTime = System.currentTimeMillis();
                        // Log.v(TAG, "---------***-------- endTime = " +
                        // endTime + " startTime = " + startTime +
                        // " bAnimationPause = " + bAnimationPause);
                        if ((endTime - startTime > FRAME_DURATION) && !bAnimationPause) {
                            // Log.v(TAG,
                            // "---------***-------- bitmapList.size() = " +
                            // bitmapList.size() + "  sendIndex = " +
                            // sendIndex);
                            startTime = endTime;
                            if ((bitmapList.size() > sendIndex)
                                    && (bitmapList.get(sendIndex) != null)) {
                                // Log.v(TAG,
                                // "---------***-------- change bitmap");
                                Message msg = new Message();
                                Bundle b = new Bundle();
                                b.putInt("index", sendIndex);
                                msg.setData(b);
                                // Log.v(TAG,
                                // "---------***-------- bLoadBreak = " +
                                // bLoadBreak);
                                // Log.v(TAG, " ");
                                if (!bLoadBreak) {
                                    iconAnimationHandler.sendMessage(msg);
                                }
                                if (sendIndex < ICON_FRAME_NUM - 1) {
                                    sendIndex++;
                                } else {
                                    sendIndex = 0;
                                }
                            }
                        } else if (endTime - startTime < 0) {
                            startTime = endTime;
                        }
                        // Log.v(TAG, "\n\n");
                        // Log.v(TAG, "---------***-------- suspendFlag = " +
                        // suspendFlag);

                        synchronized (this) {
                            while (suspendFlag) {
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    // Log.v(TAG, "\n  recycle=====\n");
                    synchronized (bLoadBreak) {
                        for (Bitmap bmp : bitmapList) {
                            if (bmp != null) {
                                bmp.recycle();
                                bmp = null;
                            }
                        }
                        bitmapList.clear();
                        bLoadBreak = false;
                    }
                    bLoadComplete = false;
                } else {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class InputSourceThread implements Runnable {

        @Override
        public void run() {
            EnumInputSource tmpInputSource;
            int tmpScale;
            while (!bExitThread) {
                synchronized (bSync) {
                    tmpInputSource = toChangeInputSource;
                    toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_NONE;
                    tmpScale = fullScale;
                    fullScale = SCALE_NONE;
                }
                if ((tmpInputSource != EnumInputSource.E_INPUT_SOURCE_NONE)) {
                    Log.v(TAG, "\n  change source=====" + tmpInputSource);
                    EnumInputSource currentSource;
                    if (tmpInputSource == EnumInputSource.E_INPUT_SOURCE_ATV
                            || (Tools.isBox() && tmpInputSource == EnumInputSource.E_INPUT_SOURCE_DTV)) {
                        currentSource = TvCommonManager.getInstance().getCurrentInputSource();
                        Log.v(TAG, "currentSource=====" + currentSource);
                        if (currentSource.equals(EnumInputSource.E_INPUT_SOURCE_STORAGE)) {
                            int curSource = TvCommonManager.getInstance().getPowerOnSource().ordinal();                            Log.v(TAG, "minidatabase=====" + curSource);
                            if ((curSource >= 0)
                                    && (curSource <= EnumInputSource.E_INPUT_SOURCE_NONE.ordinal())) {
                                Log.v(TAG, "setInputSource====="
                                        + EnumInputSource.values()[curSource]);
                                TvCommonManager.getInstance().setInputSource(EnumInputSource.values()[curSource]);
                                if (EnumInputSource.values()[curSource] == EnumInputSource.E_INPUT_SOURCE_ATV) {
                                    int channel = TvChannelManager.getInstance().getCurrentChannelNumber();
                                    if ((channel < 0) || (channel > 255)) {
                                        channel = 0;
                                    }
                                    TvChannelManager.getInstance().setAtvChannel(channel);
                                } else if (EnumInputSource.values()[curSource] == EnumInputSource.E_INPUT_SOURCE_DTV) {
                                    //if (!Tools.isBox()) {
                                        TvChannelManager.getInstance().changeToFirstService(
                                            EnumFirstServiceInputType.E_FIRST_SERVICE_DTV,
                                            EnumFirstServiceType.E_DEFAULT);
                                    //}
                                }
                            }
                        }
                    } else {
                        TvCommonManager.getInstance().setInputSource(tmpInputSource);
                    }
                }
                if (tmpScale == SCALE_SMALL) {
                    setPipscale();
                } else if (tmpScale == SCALE_FULL) {
                    setFullscale();
                }
                hasTVClick = false;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDDI();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        setContentView(R.layout.main);
        Settings.System.putInt(getContentResolver(), "home_hot_key_disable", 0);
        initViewPager();
        initGridView();
        initIdx();
        msContext = this;
        mGridView1.requestFocus(0);
        initmainbutton();
        mcontent = (LinearLayout) findViewById(R.id.mainlayout);
        mcontent.setVisibility(View.INVISIBLE);
        updateWallpaperVisibility(false);// hide wallpaper and launcher tabletop
        addAppWidgetView();
        if (ThirdPartyDtvValue == 0) {
            new Thread(inputSourceThread).start();
        }
        delay2LaunchAppWhenSupernovaNotOkHandler = new Delay2LaunchAppWhenSupernovaNotOkHandler();

        if (Tools.isBox()) {
            FileFlyReceiver ffr = new FileFlyReceiver();
            IntentFilter iFilter = new IntentFilter();
            iFilter.addAction(FILE_FLY_LAUNCH);
            registerReceiver(ffr, iFilter);
        }

        //ljq add
        updateUIBroadcastReceiver = new UpdateUIBroadcastReceiver();
        IntentFilter mintentFilter = new IntentFilter();
        mintentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        mintentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        mintentFilter.addDataScheme("file");
        registerReceiver(updateUIBroadcastReceiver,mintentFilter);

        wifiBrocasRceiver = new wifiBrocasRceiver();
        IntentFilter wifiintentFilter = new IntentFilter();
        wifiintentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(wifiBrocasRceiver,wifiintentFilter);
        Log.d(TAG, "onCreate: liangjianqin");
        //ljq add end

        imageTV.requestFocus();


    }

    private void resetGridViewSelection() {
        if (!mGridView1.isFocused()) {
            mGridView1.setSelection(-1);
        }
        if (!mGridView2.isFocused()) {
            mGridView2.setSelection(-1);
        }
        if (!mGridView3.isFocused()) {
            mGridView3.setSelection(-1);
        }
    }

    /*
     * private void addAppWidgetView() { mAppWidgetManager =
     * AppWidgetManager.getInstance(getApplicationContext()); mAppWidgetHost =
     * new AppWidgetHost(getApplicationContext(), APPWIDGET_HOST_ID);
     * mAppWidgetHost.startListening(); ArrayList<AppWidgetProviderInfo>
     * mAppwidgetProviderInfos = new ArrayList<AppWidgetProviderInfo>();
     * mAppwidgetProviderInfos = (ArrayList<AppWidgetProviderInfo>)
     * mAppWidgetManager.getInstalledProviders(); for (int i = 0; i <
     * mAppwidgetProviderInfos.size(); i++) { Log.i(TAG,
     * "------------------>label = " + mAppwidgetProviderInfos.get(i).label); if
     * (mAppwidgetProviderInfos.get(i).label.equalsIgnoreCase("BabaoNews")) {
     * appWidgetInfo1 = mAppwidgetProviderInfos.get(i); Log.i(TAG,
     * "------------------>appWidgetId1 = " + appWidgetId1); } if
     * (mAppwidgetProviderInfos.get(i).label.equalsIgnoreCase("BabaoWeather")) {
     * appWidgetInfo2 = mAppwidgetProviderInfos.get(i); Log.i(TAG,
     * "------------------>appWidgetId2 = " + appWidgetId2); } if
     * (mAppwidgetProviderInfos.get(i).label.equalsIgnoreCase("RecommendApp")) {
     * appWidgetInfo3 = mAppwidgetProviderInfos.get(i); Log.i(TAG,
     * "------------------>appWidgetId3 = " + appWidgetId3); } } if
     * (appWidgetInfo1 != null) { ComponentName cn1 = new
     * ComponentName("com.biaoqi.stbwidget.host",
     * "com.biaoqi.stbwidget.host.BabaoWebWidget"); appWidgetId1 =
     * mAppWidgetHost.allocateAppWidgetId(); // appWidgetInfo1 =
     * mAppWidgetManager.getAppWidgetInfo(appWidgetId1) // ;
     * mAppWidgetManager.bindAppWidgetId(appWidgetId1, cn1); appWidgetInfo1 =
     * mAppWidgetManager.getAppWidgetInfo(appWidgetId1); hostView1 =
     * mAppWidgetHost.createView(this, appWidgetId1, appWidgetInfo1); ablp1 =
     * new AbsoluteLayout.LayoutParams(dimens2px(R.integer.Widget_width),
     * dimens2px(R.integer.Widget_height), dimens2px(R.integer.webWidget_x),
     * dimens2px(R.integer.webWidget_y)); hostView1.setFocusable(true);
     * hostView1.setOnFocusChangeListener(new onAppWidgetFocusChangeListener());
     * hostView1.setClickable(true); hostView1.setOnClickListener(new
     * OnAppWidgetClickListener());
     * hostView1.setBackgroundResource(R.drawable.widgeticon);
     * hostView1.setClipToPadding(false); ((ViewGroup) view1).addView(hostView1,
     * ablp1); } if (appWidgetInfo2 != null) { ComponentName cn2 = new
     * ComponentName("com.biaoqi.stbwidget.host",
     * "com.biaoqi.stbwidget.host.WeatherWidgetProvider"); appWidgetId2 =
     * mAppWidgetHost.allocateAppWidgetId(); // appWidgetInfo2 =
     * mAppWidgetManager.getAppWidgetInfo(appWidgetId2) // ;
     * mAppWidgetManager.bindAppWidgetId(appWidgetId2, cn2); appWidgetInfo2 =
     * mAppWidgetManager.getAppWidgetInfo(appWidgetId2); hostView2 =
     * mAppWidgetHost.createView(this, appWidgetId2, appWidgetInfo2); ablp2 =
     * new AbsoluteLayout.LayoutParams(dimens2px(R.integer.Widget_width),
     * dimens2px(R.integer.Widget_height), dimens2px(R.integer.WeatherWidget_x),
     * dimens2px(R.integer.WeatherWidget_y)); hostView2.setFocusable(true);
     * hostView2.setOnFocusChangeListener(new onAppWidgetFocusChangeListener());
     * hostView2.setClickable(true); hostView2.setOnClickListener(new
     * OnAppWidgetClickListener());
     * hostView2.setBackgroundResource(R.drawable.widgeticon);
     * hostView2.setClipToPadding(false); ((ViewGroup) view1).addView(hostView2,
     * ablp2); } if (appWidgetInfo3 != null) { ComponentName cn3 = new
     * ComponentName("com.mstar.appwidget",
     * "com.mstar.appwidget.ReAppWidgetProvider"); appWidgetId3 =
     * mAppWidgetHost.allocateAppWidgetId(); // appWidgetInfo2 =
     * mAppWidgetManager.getAppWidgetInfo(appWidgetId2) // ;
     * mAppWidgetManager.bindAppWidgetId(appWidgetId3, cn3); appWidgetInfo3 =
     * mAppWidgetManager.getAppWidgetInfo(appWidgetId3); hostView3 =
     * mAppWidgetHost.createView(this, appWidgetId3, appWidgetInfo3); ablp3 =
     * new AbsoluteLayout.LayoutParams(dimens2px(R.integer.Widget_width),
     * dimens2px(R.integer.Widget_height), dimens2px(R.integer.appWidget_x),
     * dimens2px(R.integer.appWidget_y)); hostView3.setFocusable(true);
     * hostView3.setOnFocusChangeListener(new onAppWidgetFocusChangeListener());
     * hostView3.setBackgroundResource(R.drawable.widgeticon);
     * hostView3.setClipToPadding(false); hostView3.setClickable(true);
     * hostView3.setOnClickListener(new OnAppWidgetClickListener());
     * ((ViewGroup) view1).addView(hostView3, ablp3); } }
     */

    private void initDDI() {
        try {
            if (TvManager.getInstance() != null) {
                String Value = TvManager.getInstance().getEnvironment(ThirdPartyDtvEnv);

                if (Value != null && Value.length() != 0) {
                    ThirdPartyDtvValue = Integer.valueOf(Value).intValue();
                }

                Log.i(TAG, "<<<<<< thirdpartydtv env : >>>>>>>>>>>" + ThirdPartyDtvValue);
            }

        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    private void addAppWidgetView() {
        AppWidgetManager mAppWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        AppWidgetHost mAppWidgetHost = new AppWidgetHost(getApplicationContext(), APPWIDGET_HOST_ID);
        mAppWidgetHost.startListening();
        ArrayList<AppWidgetProviderInfo> mAppwidgetProviderInfos = (ArrayList<AppWidgetProviderInfo>) mAppWidgetManager
                .getInstalledProviders();
        android.content.res.Resources res = getResources();
        int w = dimens2px(R.integer.Widget_width);
        int h = dimens2px(R.integer.Widget_height);
        onAppWidgetFocusChangeListener focusChangeListener = new onAppWidgetFocusChangeListener();
        OnAppWidgetClickListener clickListener = new OnAppWidgetClickListener();

        for (int i = 0; i < mAppwidgetProviderInfos.size(); i++) {
            AppWidgetProviderInfo appWidgetInfo = mAppwidgetProviderInfos.get(i);
            Log.i(TAG, "------------------>label = " + appWidgetInfo.label);
            if (appWidgetInfo.label.equalsIgnoreCase("BabaoNews")) {// BabaoNews
                int x = dimens2px(R.integer.webWidget_x);
                int y = dimens2px(R.integer.webWidget_y);
                hostView1 = getAppWidgetHostView("com.biaoqi.stbwidget.host",
                        "com.biaoqi.stbwidget.host.BabaoWebWidget", mAppWidgetManager,
                        mAppWidgetHost, appWidgetInfo, focusChangeListener, clickListener, w, h, x,
                        y);
            } else if (appWidgetInfo.label.equalsIgnoreCase("BabaoWeather")) {// BabaoWeather
                int x = dimens2px(R.integer.WeatherWidget_x);
                int y = dimens2px(R.integer.WeatherWidget_y);
                hostView2 = getAppWidgetHostView("com.biaoqi.stbwidget.host",
                        "com.biaoqi.stbwidget.host.WeatherWidgetProvider", mAppWidgetManager,
                        mAppWidgetHost, appWidgetInfo, focusChangeListener, clickListener, w, h, x,
                        y);
            } else if (appWidgetInfo.label.equalsIgnoreCase("RecommendApp")) {
                int x = dimens2px(R.integer.appWidget_x);
                int y = dimens2px(R.integer.appWidget_y);
                hostView3 = getAppWidgetHostView("com.mstar.appwidget",
                        "com.mstar.appwidget.ReAppWidgetProvider", mAppWidgetManager,
                        mAppWidgetHost, appWidgetInfo, focusChangeListener, clickListener, w, h, x,
                        y);
            }
        }

    }

    private AppWidgetHostView getAppWidgetHostView(String packageName, String className,
            AppWidgetManager mAppWidgetManager, AppWidgetHost mAppWidgetHost,
            AppWidgetProviderInfo appWidgetProviderInfo,
            onAppWidgetFocusChangeListener focusChangeListener,
            OnAppWidgetClickListener clickListener, int wgtWidth, int wgtHeight, int wgtX, int wgtY) {

        ComponentName cn = new ComponentName(packageName, className);

        int appWidgetId = queryAppwidgetId(className);
        Log.i(TAG, className + "-------queryWidgetId-----------> = " + appWidgetId);
        Log.i(TAG,
                className + "-------querywidgetIs-----------> = "
                        + (mAppWidgetManager.getAppWidgetInfo(appWidgetId) == null));
        if (appWidgetId == -1 || mAppWidgetManager.getAppWidgetInfo(appWidgetId) == null) {
            Log.i(TAG, className + "------allocateAppWidgetId------------> = " + appWidgetId);
            appWidgetId = mAppWidgetHost.allocateAppWidgetId();
            mAppWidgetManager.bindAppWidgetId(appWidgetId, cn);
            saveAppwidgetId(className, appWidgetId);
        }
        Log.i(TAG, className + "-------widgetId-----------> = " + appWidgetId);
        AppWidgetHostView hostView = mAppWidgetHost.createView(this, appWidgetId,
                appWidgetProviderInfo);
        hostView.setFocusable(true);
        hostView.setOnFocusChangeListener(focusChangeListener);
        hostView.setClickable(true);
        hostView.setOnClickListener(clickListener);
        hostView.setBackgroundResource(R.drawable.widgeticon);
        hostView.setClipToPadding(false);

        @SuppressWarnings("deprecation")
        android.widget.AbsoluteLayout.LayoutParams ablp = new android.widget.AbsoluteLayout.LayoutParams(
                wgtWidth, wgtHeight, wgtX, wgtY);

        ((ViewGroup) view1).addView(hostView, ablp);
        return hostView;
    }

    private int queryAppwidgetId(String className) {
        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        int id = settings.getInt(className, -1);
        return id;
    }

    private void saveAppwidgetId(String className, int id) {
        SharedPreferences uiState = getPreferences(0);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(className, id);
        editor.commit();
    }

    private int dimens2px(int id) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (getResources().getInteger(id) * scale + 0.5f);
    }

    private void getAllApps() {
        final PackageManager packageManager = getPackageManager();
        final Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        listApps = packageManager.queryIntentActivities(mIntent, 0);
    }

    private void initGridView() {
        getAllApps();
        mGridPageAdapter1 = new GridPageAdapter(this, listApps, 2);
        mGridPageAdapter2 = new GridPageAdapter(this, listApps, 1);
        mGridPageAdapter3 = new GridPageAdapter(this, listApps, 3);
        mGridView1 = (GridView) view1.findViewById(R.id.gridview1);
        mGridView2 = (GridView) view2.findViewById(R.id.gridview2);
        mGridView3 = (GridView) view3.findViewById(R.id.gridview3);
        mGridView1.setAdapter(mGridPageAdapter1);
        mGridView2.setAdapter(mGridPageAdapter2);
        mGridView3.setAdapter(mGridPageAdapter3);
        mGridView1.setOnItemClickListener(new ItemclickListener());
        mGridView2.setOnItemClickListener(new ItemclickListener());
        mGridView3.setOnItemClickListener(new ItemclickListener());
        mGridView1.setOnItemSelectedListener(new OnItemSelectPage1());
        mGridView2.setOnItemSelectedListener(new OnItemSelectPage());
        mGridView3.setOnItemSelectedListener(new OnItemSelectPage());
        mGridView1.setOnTouchListener(statusBarShowListener);
        mGridView2.setOnTouchListener(statusBarShowListener);
        mGridView3.setOnTouchListener(statusBarShowListener);
    }

    private void initViewPager() {
        // mViewPager = (MstarViewPager) findViewById(R.id.viewpager);
        absoluteLayout = (AbsoluteLayout) findViewById(R.id.absolutelayout);
        mListView = new ArrayList<View>();
        mInflater = getLayoutInflater();
        view2 = mInflater.inflate(R.layout.page2, null);
        mListView.add(view2);
        view1 = mInflater.inflate(R.layout.page1, null);
        mListView.add(view1);
        view3 = mInflater.inflate(R.layout.page3, null);
        mListView.add(view3);
        mViewPager = (MstarViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new ViewPagerExaAdapter());
        mViewPager.setCurrentItem(PAGE2);
        currentpageidx = PAGE2;
        itemidxpage2 = 0;
        mViewPager.setOnPageChangeListener(new mPageChangeListener());
        mViewPager.setOnTouchListener(statusBarShowListener);
        absoluteLayout.setOnTouchListener(statusBarShowListener);
    }

    private void initIdx() {
        idxImageViews = new ArrayList<ImageView>();
        pageidx1 = (ImageView) findViewById(R.id.pageidx1);
        idxImageViews.add(pageidx1);
        pageidx2 = (ImageView) findViewById(R.id.pageidx2);
        idxImageViews.add(pageidx2);
        pageidx3 = (ImageView) findViewById(R.id.pageidx3);
        idxImageViews.add(pageidx3);
        idxImageViews.get(0).setImageResource(R.drawable.idx);
        idxImageViews.get(0).setScaleX(0.75f);
        idxImageViews.get(0).setScaleY(0.75f);
        idxImageViews.get(1).setImageResource(R.drawable.focus_idx);
        idxImageViews.get(1).setScaleX(0.75f);
        idxImageViews.get(1).setScaleY(0.75f);
        idxImageViews.get(2).setImageResource(R.drawable.idx);
        idxImageViews.get(2).setScaleX(0.75f);
        idxImageViews.get(2).setScaleY(0.75f);
    }

    public void initmainbutton() {
        dockBtns = new ArrayList<Button>();
        buttonAllapp = (Button) findViewById(R.id.allapps);
        dockBtns.add(buttonAllapp);
        buttonAllapp.setOnClickListener(new AllAppClickListener());
        buttonAllapp.setOnFocusChangeListener(new onMainItemFocusChangeListener());
        buttonAppstore = (Button) findViewById(R.id.appstore);
        dockBtns.add(buttonAppstore);
        buttonAppstore.setOnClickListener(new AppStoreClickListener());
        buttonAppstore.setOnFocusChangeListener(new onMainItemFocusChangeListener());
        buttonBrowser = (Button) findViewById(R.id.browser);
        dockBtns.add(buttonBrowser);
        buttonBrowser.setOnClickListener(new BrowserClickListener());
        buttonBrowser.setOnFocusChangeListener(new onMainItemFocusChangeListener());
        buttonSetting = (Button) findViewById(R.id.setting);
        dockBtns.add(buttonSetting);
        buttonSetting.setOnClickListener(new SettingClickListener());
        buttonSetting.setOnFocusChangeListener(new onMainItemFocusChangeListener());
        buttonTV = (Button) findViewById(R.id.tvsetting);
        dockBtns.add(buttonTV);
        buttonTV.setOnClickListener(new TVSourceClickListener());
        buttonTV.setOnFocusChangeListener(new onMainItemFocusChangeListener());
        buttonMM = (Button) findViewById(R.id.localmm);
        dockBtns.add(buttonMM);
        buttonMM.setOnClickListener(new LocalMMClickListener());
        buttonMM.setOnFocusChangeListener(new onMainItemFocusChangeListener());
        bExitThread = false;
        iconAnimationHandler = new IconAnimationHandler();
        new Thread(loadIconThread).start();
        buttonzixun = (TextView) view1.findViewById(R.id.buttonzixun);
        buttontianqi = (TextView) view1.findViewById(R.id.buttontianqi);
        buttonxinxi = (Button) view1.findViewById(R.id.buttonxinxi);
        buttonxinxi.setOnClickListener(new webContentClickListener());
        //ljq add
        buttonyinyong = (Button) view1.findViewById(R.id.buttonyinyong);
        textViewTime = (TextView)view1.findViewById(R.id.mlancher_text_time);
        showTimeHandler = new ShowTimeHandler();
        new Thread(showTimeThread).start();
        //ljq add end


        imageTV = (Button) view1.findViewById(R.id.imagetv);
        imageTV.setOnClickListener(new TVSourceClickListener());
    }

    private AlertDialog getAlertDialog(String title){
        Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setSingleChoiceItems(laucherInputsources, this.getEnablePipFlagIndex(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                String toastMesage = "";
                setEnablePipFlagIndex(whichButton);
                toastMesage = "Launcher Pip input source : " + laucherInputsources[whichButton].toString();
                Toast.makeText(LauncherActivity.this, toastMesage, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("OK", null);
        return builder.create();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "+++++++++++++++++++++down+++++++++++++++++++++");
        try {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                if (buttonAllapp.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_LEFT 1++++++++");
					buttonAppstore.requestFocus();
                    // buttonMM.requestFocus();
                    return true;
                }
                if (buttonAppstore.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_LEFT 2++++++++");
					buttonBrowser.requestFocus();
                    // buttonAllapp.requestFocus();
                    return true;
                }
                if (buttonBrowser.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_LEFT 3++++++++");
					buttonTV.requestFocus();
                    // buttonAppstore.requestFocus();
                    return true;
                }
                if (buttonSetting.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_LEFT 4++++++++");
					buttonMM.requestFocus();
                    // buttonBrowser.requestFocus();
                    return true;
                }
                if (buttonTV.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_LEFT 5++++++++");
					buttonSetting.requestFocus();
                    // buttonSetting.requestFocus();
                    return true;
                }
                if (buttonMM.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_LEFT 6++++++++");
					buttonAllapp.requestFocus();
                    // buttonTV.requestFocus();
                    return true;
                }
                if (hostView1.isFocused()) {
                    buttonxinxi.requestFocus();
                    return true;
                }
                if (hostView3.isFocused()) {
                    hostView2.requestFocus();
                    return true;
                }
                return true;
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (buttonAllapp.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_RIGHT 1++++++++");
					buttonMM.requestFocus();
                    // buttonAppstore.requestFocus();
                    return true;
                }
                if (buttonAppstore.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_RIGHT 2++++++++");
					buttonAllapp.requestFocus();
                    // buttonBrowser.requestFocus();
                    return true;
                }
                if (buttonBrowser.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_RIGHT 3++++++++");
					buttonAppstore.requestFocus();
                    // buttonSetting.requestFocus();
                    return true;
                }
                if (buttonSetting.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_RIGHT 4++++++++");
                    buttonTV.requestFocus();
					// buttonTV.requestFocus();
                    return true;
                }
                if (buttonTV.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_RIGHT 5++++++++");
					buttonBrowser.requestFocus();
                    // buttonMM.requestFocus();
                    return true;
                }
                if (buttonMM.isFocused()) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_RIGHT 6++++++++");
                    buttonSetting.requestFocus();
					// buttonAllapp.requestFocus();
                    return true;
                }
                if (hostView2.isFocused()) {
                    hostView3.requestFocus();
                    return true;
                }
                if (buttonxinxi.isFocused()) {
                    hostView1.requestFocus();
                    return true;
                }
                return true;
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                if (imageTV.isFocused()) {
                    Log.i(TAG, "+++++++++++hostView1 request focus++++++++");
                    hostView1.requestFocus();
                    return true;
                }
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                if (buttonAllapp.isFocused() && currentpageidx == PAGE2) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_UP 1++++++++");
                    hostView2.requestFocus();
                    return true;
                }
                if (buttonAppstore.isFocused() && currentpageidx == PAGE2) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_UP 2++++++++");
                    hostView2.requestFocus();
                    return true;
                }
                if (buttonBrowser.isFocused() && currentpageidx == PAGE2) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_UP 3++++++++");
                    hostView2.requestFocus();
                    return true;
                }
                if (buttonSetting.isFocused() && currentpageidx == PAGE2) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_UP 4++++++++");
                    hostView3.requestFocus();
                    return true;
                }
                if (buttonTV.isFocused() && currentpageidx == PAGE2) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_UP 5++++++++");
                    hostView3.requestFocus();
                    return true;
                }
                if (buttonMM.isFocused() && currentpageidx == PAGE2) {
                    Log.i(TAG, "+++++++++++KEYCODE_DPAD_UP 6++++++++");
                    hostView3.requestFocus();
                    return true;
                }
            }
            if (keyCode == KeyEvent.KEYCODE_MENU && (imageTV.isFocused())) {
                final AlertDialog alertDialog = getAlertDialog("Launcher InputSource Setting");
                alertDialog.show();
            }
            if (keyCode == KeyEvent.KEYCODE_0) {
                Log.i(TAG, "+++++++++++++++KEYCODE_RED++++++++++++++++");
                int totalitem = 0;
                if (currentpageidx == PAGE2) {
                    focusIdx = mGridView1.getSelectedItemPosition();
                    totalitem = mGridPageAdapter1.getCount();
                } else if (currentpageidx == PAGE1) {
                    focusIdx = mGridView2.getSelectedItemPosition();
                    totalitem = mGridPageAdapter2.getCount();
                } else if (currentpageidx == PAGE3) {
                    focusIdx = mGridView3.getSelectedItemPosition();
                    totalitem = mGridPageAdapter3.getCount();
                }
                if (totalitem == 0 || (focusIdx<0)) {
                    Toast.makeText(LauncherActivity.this,
                            LauncherActivity.this.getResources().getString(R.string.this_page_no_icon),
                            Toast.LENGTH_SHORT).show();
                } if (isWidgetFocus || isMainItemsFocus || imageTV.isFocused()) {
                    // if focus icon is widget or mainitems or imagetv then don't do any thing.
                    return true;
                } else {
                    AlertDialog.Builder ad = new AlertDialog.Builder(msContext);
                    ad.setTitle(LauncherActivity.this.getResources().getString(R.string.deltitle));
                    ad.setMessage(LauncherActivity.this.getResources().getString(R.string.delmessage));
                    ad.setPositiveButton(
                            LauncherActivity.this.getResources().getString(R.string.delconfirm),
                            new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                try {
                                    if (currentpageidx == PAGE2) {
                                        mGridPageAdapter1.removeItemIcon(LauncherActivity.this.focusIdx);
                                    } else if (currentpageidx == PAGE1) {
                                        mGridPageAdapter2.removeItemIcon(LauncherActivity.this.focusIdx);
                                    } else if (currentpageidx == PAGE3) {
                                        mGridPageAdapter3.removeItemIcon(LauncherActivity.this.focusIdx);
                                    }
                                } catch (Exception ex) {
                                    Log.d(TAG, "Fail  ~~");
                                }
                            }});
                    ad.setNegativeButton(
                            LauncherActivity.this.getResources().getString(R.string.delcancel),
                            new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                            }
                            });
                    ad.show();
                }
                return true;
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                if (imageTV.isFocused())
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if(keyCode== KeyEvent.KEYCODE_MENU)
        // startWallpaper();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        TextView appmodeltext = null;
        super.onConfigurationChanged(newConfig);
        // /////////////app list////////////////////////////
        mGridPageAdapter1.notifyDataSetChanged();
        mGridPageAdapter2.notifyDataSetChanged();
        mGridPageAdapter3.notifyDataSetChanged();
        // //////////////middle widget/////////////////////
        setText(R.id.allapstext, R.string.allapp);
        setText(R.id.apstoretext, R.string.store);
        setText(R.id.browsertext, R.string.browser);
        setText(R.id.settingtext, R.string.setting);
        setText(R.id.tvsettingtext, R.string.tvsource);
        setText(R.id.localmmtext, R.string.localmm);
        // ///////bottom app model///////////////
        setText(R.id.buttonzixun, R.string.zixun);
        setText(R.id.buttontianqi, R.string.tianqi);
        setText(R.id.buttonyinyongtext, R.string.yingyongtuijian);
        setText(R.id.buttonxinxitext, R.string.gonggongxinxi);
        Log.i(TAG, "+++++++++++onConfigurationChanged+++++++++++");
    }

    private void setText(int resId, int stringId) {
        TextView appmodeltext = (TextView) findViewById(resId);
        if (appmodeltext != null) {
            appmodeltext.setText(stringId);
            appmodeltext.setTextSize(15.0f);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.i(TAG, "+++++++++++++++++++++up+++++++++++++++++++++");
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            if ((buttonAllapp.isFocused()) || (buttonAppstore.isFocused())
                    || (buttonBrowser.isFocused()) || (buttonSetting.isFocused())
                    || (buttonTV.isFocused()) || (buttonMM.isFocused())) {
                Log.i(TAG, "+++++++++++focus3Dmodel++++++++");
                return true;
            }
            if (!imageTV.isFocused()) {
                getItemPosition(currentpageidx);
            }
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            if ((buttonAllapp.isFocused()) || (buttonAppstore.isFocused())
                    || (buttonBrowser.isFocused()) || (buttonSetting.isFocused())
                    || (buttonTV.isFocused()) || (buttonMM.isFocused())) {
                Log.i(TAG, "+++++++++++focus3Dmodel++++++++");
                return true;
            } else if (!((currentpageidx == PAGE3) && (mGridView3.getSelectedItemPosition() == -1))) {
                getItemPosition(currentpageidx);
            }
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            if (imageTV.isFocused())
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    Handler handlertv = new Handler();

    Runnable handlerRuntv = new Runnable() {

        @Override
        public void run() {
            try {
                // setPipscale();
                LinearLayout surfaceViewLayout = (LinearLayout) view1
                        .findViewById(R.id.tv_surfaceview_layout);
                surfaceViewLayout.removeAllViews();
                surfaceView = new SurfaceView(LauncherActivity.this);
                surfaceView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
                surfaceViewLayout.addView(surfaceView);
                surfaceView.setBackgroundResource(R.drawable.background);
                Log.d(TAG, "openSurfaceView");
                openSurfaceView();
                setPipscale();
                if (surfaceView != null)
                    surfaceView.setBackgroundColor(Color.TRANSPARENT);
                if (surfaceView.getVisibility() == View.VISIBLE && currentpageidx != PAGE2) {
                    surfaceView.setVisibility(View.INVISIBLE);
                } else if (currentpageidx == PAGE2) {
                    surfaceView.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handlertv.removeCallbacks(handlerRuntv);
        }
    };

    Runnable pip_thread = new Runnable() {

        @Override
        public void run() {
            try {
                if (surfaceView.getVisibility() != View.VISIBLE && currentpageidx == PAGE2) {
                    surfaceView.setVisibility(View.VISIBLE);
                }
                Log.i(TAG, "..PipThread..");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    // delay enableHomekey
    Runnable enable_homekey = new Runnable() {

        @Override
        public void run() {
            Log.v(TAG, "Sky3DHomeActivity enable_homekey");
            Settings.System.putInt(getContentResolver(), "home_hot_key_disable", 0);
        }
    };

    @Override
    protected void onRestart() {
        loadIconThread.myResume();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "----------onPause----------");
        handlertv.removeCallbacks(pip_thread);
        super.onPause();
        isPause = true;
        bAnimationPause = true;
        /*
         * if
         * (STR_STATUS_SUSPENDING.equals(SystemProperties.get("mstar.str.suspending"
         * , "0"))) { Log.d(TAG,
         * "---- mstar.str.suspending == 1, onPasue do nothing ----");
         * LinearLayout surfaceViewLayout = (LinearLayout)
         * view1.findViewById(R.id.tv_surfaceview_layout);
         * surfaceViewLayout.removeAllViews(); if (surfaceView != null) {
         * surfaceView
         * .getHolder().removeCallback((android.view.SurfaceHolder.Callback)
         * callback); surfaceView = null; } //
         * mcontent.setVisibility(View.INVISIBLE); //
         * updateWallpaperVisibility(false); }else {
         */
        delay2LaunchAppWhenSupernovaNotOkHandler.removeMessages(SET_WIN_SCALE);
        if (surfaceView != null) {
            if (createsurface) {
                // surfaceView.setVisibility(View.INVISIBLE);
            }
            if (bSystemShutdown == false && mixKey) {
                if (STR_STATUS_NONE.equals(SystemProperties.get("mstar.str.suspending", "0"))) {
                    //synchronized (bSync) {
                    //    fullScale = SCALE_FULL;
                    //}
                }
            } else {
                bSystemShutdown = false;
            }
        } else {
            Log.d(TAG, "---- removeCallbacks(handlerRuntv) ----");
            handlertv.removeCallbacks(handlerRuntv);
        }
        handlertv.postDelayed(enable_homekey, 800);
        // }
    }

    private void ClearTVManager_RestorSTR() {
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().finalizeAllManager();
            }
        } catch (Throwable e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private void SetPropertyForSTR(String value) {
        IWindowManager winService = IWindowManager.Stub.asInterface(ServiceManager
                .checkService(Context.WINDOW_SERVICE));
        if (winService == null) {
            Log.w(TAG, "Unable to find IWindowManger interface.");
        } else {
            SystemProperties.set("mstar.str.suspending", value);
            // setSystemProperties is not avaliable rightnow, open when it's
            // ready.
            // try {
            // winService.setSystemProperties("mstar.str.suspending", value);
            // } catch (RemoteException e) {
            // e.printStackTrace();
            // }
        }
    }

    @Override
    protected void onResume() {
        hasTVClick = false;
        Log.i(TAG, "<<<<<<----------enter onResume---------->>>>>" + hasTVClick);
        super.onResume();
        isPause = false;
        SystemProperties.set("mstar.str.storage", "0");
        imageTV.requestFocus();
        resetGridViewSelection();
        /*
         * if (surfaceView != null && hasTVClick) {
         * surfaceView.setBackgroundColor(Color.TRANSPARENT); hasTVClick =
         * false; }
         */

        try {
            if (TvManager.getInstance() != null) {
                Log.i(TAG,"<<<<<<----------TvManager.getInstance() != null ---------->>>>>hammm");
             EnumPipModes mode = TvManager.getInstance().getPipManager().getPipMode();
                if (mode == EnumPipModes.E_PIP_MODE_PIP) {
                    Log.i(TAG, "<<<<<<----------mode == EnumPipModes.E_PIP_MODE_PIP ---------->>>>>");
                    TvPipPopManager.getInstance().disablePip();
                    TvPipPopManager.getInstance().setPipOnFlag(false);
                } else if (mode == EnumPipModes.E_PIP_MODE_POP) {
                    Log.i(TAG, "<<<<<<----------mode == EnumPipModes.E_PIP_MODE_POP ---------->>>>>");
                    Enum3dType formatType = Enum3dType.EN_3D_TYPE_NUM;
                    try {
                        formatType = TvManager.getInstance().getThreeDimensionManager()
                                .getCurrent3dFormat();
                        Log.i(TAG, "<<<<<<----------formatType ---------->>>>>");
                    } catch (TvCommonException e) {
                        e.printStackTrace();
                    }

                    if (formatType == Enum3dType.EN_3D_DUALVIEW) {
                        Log.i(TAG,
                                "<<<<<<----------formatType == Enum3dType.EN_3D_DUALVIEW ---------->>>>>");
                        TvPipPopManager.getInstance().disable3dDualView();
                    } else {
                        Log.i(TAG,
                                "<<<<<<----------formatType != Enum3dType.EN_3D_DUALVIEW ---------->>>>>");
                        TvPipPopManager.getInstance().disablePop();
                    }
                }
            }
           }
           catch (TvCommonException e) {
            e.printStackTrace();
        }
        /***** Ended by gerard.jiang 2013/05/02 *****/

        if (isPowerOn() == true && ThirdPartyDtvValue == 0 && (!Tools.isBox())) {
            Log.i(TAG, "<<<<<<----------PowerOn == true ---------->>>>>");

            ComponentName componentName = new ComponentName("com.mstar.tv.tvplayer.ui",
                    "com.mstar.tv.tvplayer.ui.RootActivity");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(componentName);
            intent.putExtra("isPowerOn", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            LauncherActivity.this.startActivity(intent);
        } else {
            /**
             * "0": Normal flow (Not in STR flow or STR is not in Suspending or
             * Resuming) "1": STR is Suspending "2": STR is Resuming (Before
             * launcher does the 2nd onPause) if
             * (STR_STATUS_NONE.equals(SystemProperties.get(
             * "mstar.str.suspending", "0"))) {
             */
            Log.i(TAG, "<<<<<<----------mstar.str.suspending == STR_STATUS_NONE---------->>>>>");
            Intent it = new Intent("com.biaoqi.stb.launcherk.onresume");
            sendBroadcast(it);
            handlertv.removeCallbacks(enable_homekey);
            Settings.System.putInt(getContentResolver(), "home_hot_key_disable", 1);
            if (surfaceView == null) {
                Log.i(TAG, "<<<<<<---------- surfaceView == null---------->>>>>");
                mcontent.setVisibility(View.VISIBLE);
                updateWallpaperVisibility(true);
                handlertv.postDelayed(handlerRuntv, 300);
                BackHomeSource();
            } else {
                Log.i(TAG, "<<<<<<---------- surfaceView != null---------->>>>>");
                updateWallpaperVisibilityForResetWallPaper(true);
                BackHomeSource();
                if (STR_STATUS_NONE.equals(SystemProperties.get("mstar.str.suspending", "0"))) {
                    /*
                     * synchronized (bSync) { fullScale = SCALE_SMALL; }
                     */
                    // scale window, it is possible to conflict with close
                    // pop.so delay zoom out window
                    Message tmpMsg = new Message();
                    tmpMsg.what = SCALE_SMALL;
                    scaleWinHandler.sendMessageDelayed(tmpMsg, 10);
                }
            }
            bAnimationPause = false;
            Log.i(TAG, "Launcher (Home Key):  ========== Display Launcher =========="); // add
                                                                                        // for
                                                                                        // boot
                                                                                        // time
                                                                                        // test
            /*
             * }else if (STR_STATUS_SUSPENDING.equals(SystemProperties.get(
             * "mstar.str.suspending", "0"))) { Log.d(TAG,
             * "---- mstar.str.suspending == 1, onResume do nothing ----");
             * LinearLayout surfaceViewLayout = (LinearLayout)
             * view1.findViewById(R.id.tv_surfaceview_layout);
             * surfaceViewLayout.removeAllViews(); if (surfaceView != null) {
             * Log.d(TAG,
             * "---- mstar.str.suspending == 1, onResume do nothing  surface != null ----"
             * );
             * surfaceView.getHolder().removeCallback((android.view.SurfaceHolder
             * .Callback) callback); surfaceView = null; } //
             * mcontent.setVisibility(View.INVISIBLE); //
             * updateWallpaperVisibility(false); }
             */
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        Log.i(TAG, "<<<<<<----------end onResume---------->>>>>");
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "----------onStop----------");
        if (surfaceView != null && surfaceView.getVisibility() == View.VISIBLE) {
            // surfaceView.setVisibility(View.INVISIBLE);
            surfaceView.setBackgroundColor(Color.BLACK);
        }
        loadIconThread.mySuspend();
        super.onStop();
        Log.d(TAG, "onStop - Set hasTvClick flag as false");
        hasTVClick =false;
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "----------onDestroy----------");
        if (surfaceView != null) {
            surfaceView.getHolder().removeCallback(callback);
            surfaceView = null;
        }
        super.onDestroy();
        bExitThread = true;
        //ljq add
        isgetTime = false;
        unregisterReceiver(updateUIBroadcastReceiver);
        unregisterReceiver(wifiBrocasRceiver);
        //ljq add end
    }

    private void getItemPosition(int pageidx) {
        switch (pageidx) {
            case PAGE2:
                itemidxpage2 = mGridView1.getSelectedItemPosition();
                itemidx = -1;
                break;
            case PAGE1:
                itemidx = mGridView2.getSelectedItemPosition();
                itemidxpage2 = -1;
                mGridView1.setSelection(-1);
                break;
            case PAGE3:
                itemidx = mGridView3.getSelectedItemPosition();
                itemidxpage2 = -1;
                mGridView1.setSelection(-1);
                break;
            default:
                itemidxpage2 = -1;
                itemidx = -1;
                mGridView1.setSelection(-1);
                break;
        }
        Log.i(TAG, "--------itemidx1------->" + itemidxpage2 + "------item--->" + itemidx);
        Log.i(TAG, "==========page idx:===>" + currentpageidx);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if ((!createsurface) || (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                || (event.getKeyCode() == KeyEvent.KEYCODE_TV_INPUT)) {
            return true;
        }
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.i(TAG, "!!!!!!!!!!!!!!!!!!!!!!imageTV.isFocused!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
                if (currentpageidx == PAGE2 && imageTV.isFocused()) {
                    Log.i(TAG, "-**********left PAGE2 tv**************");
					/*
                    mViewPager.setCurrentItem(PAGE1);
                    currentpageidx = PAGE1;
                    mGridView2.requestFocus();
                    mGridView2.setSelection(0);
                    getItemPosition(PAGE1);
                    */
                    return true;
                } else if (currentpageidx == PAGE3
                        && (itemidx == 0 || itemidx == 5 || itemidx == 10 || (mGridPageAdapter3
                                .getCount() == 0))) {
                    Log.i(TAG, "-**********left PAGE3 0**************");
                    boolean dockFocus = false;
                    for (int i = 0; i < dockBtns.size(); i++) {
                        if (dockBtns.get(i).hasFocus()) {
                            dockFocus = true;
                        }
                    }
                    if (!dockFocus) {
                        mViewPager.setCurrentItem(PAGE2);
                        currentpageidx = PAGE2;
                        mGridView1.requestFocus();
                        mGridView1.setSelection(0);
                        getItemPosition(PAGE2);
                        return true;
                    }
                }
            } else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (currentpageidx == PAGE1
                        && mGridView3.isFocused()
                        && (itemidx == 4 || itemidx == 9 || itemidx == 14 || itemidx == (mGridPageAdapter2
                                .getCount() - 1))) {
                    Log.i(TAG, "-**********right PAGE1 **************");
                    mViewPager.setCurrentItem(PAGE2);
                    currentpageidx = PAGE2;
                    imageTV.requestFocus();
                    return true;
                } else if (currentpageidx == PAGE2
                        && mGridView1.isFocused()
                        && (itemidxpage2 == 9 || itemidxpage2 == 4 || itemidxpage2 == (mGridPageAdapter1
                                .getCount() - 1))) {
                    Log.i(TAG, "-**********right PAGE2 **************");
                    mViewPager.setCurrentItem(PAGE3);
                    currentpageidx = PAGE3;
                    mGridView3.requestFocus();
                    mGridView3.setSelection(0);
                    getItemPosition(PAGE3);
                    return true;
                }
            }
        }
        Log.i(TAG, "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- keycode = " + event.getKeyCode()
                + "        and MKeyEvent.KEYCODE_TV_SETTING  = " + MKeyEvent.KEYCODE_TV_SETTING);
        if (event.getKeyCode() == 26) {// power
            whichApp = -1;
            if (LauncherActivity.this.isCheckSNOKTheadRunning == false) {
                if (surfaceView != null)
                    surfaceView.setBackgroundResource(R.drawable.background);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LauncherActivity.this.isCheckSNOKTheadRunning = true;
                        for (int i = 0; i < (RETRY_COUNT * 3); i++) {
                            if (STR_STATUS_NONE.equals(SystemProperties.get("mstar.str.suspending",
                                    STR_STATUS_NONE))) {
                                Log.d("", "java know SN is ok");
                                if (waitDialog != null && waitDialog.isShowing())
                                    LauncherActivity.this.delay2LaunchAppWhenSupernovaNotOkHandler
                                            .sendEmptyMessageDelayed(DISMISS_WAITDIALOG, 1000);
                                if (LauncherActivity.this.whichApp > 0)
                                    LauncherActivity.this.delay2LaunchAppWhenSupernovaNotOkHandler
                                            .sendEmptyMessage(LauncherActivity.this.whichApp);
                                // if(surfaceView == null){
                                handlertv.postDelayed(handlerRuntv, 300);
                                // }
                                // else{
                                // if(isPause == false)
                                // LauncherActivity.this.delay2LaunchAppWhenSupernovaNotOkHandler.sendEmptyMessageDelayed(SET_WIN_SCALE,1000);
                                // }
                                break;
                            }
                            try {
                                Thread.sleep(SLEEP_TIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        LauncherActivity.this.isCheckSNOKTheadRunning = false;
                    }
                }).start();
            }
        }
        if (event.getKeyCode() == MKeyEvent.KEYCODE_TV_SETTING) {
            mixKey = false;
        } else {
            mixKey = true;
        }
        resetGridViewSelection();
        return super.dispatchKeyEvent(event);
    }

    @Override
    public View getCurrentFocus() {
        Log.i(TAG, "----------getCurrentFocus----------");
        return super.getCurrentFocus();
    }

    private class ViewPagerExaAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View allViews, int position, Object object) {
            ((MstarViewPager) allViews).removeView(mListView.get(position));
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return mListView.size();
        }

        @Override
        public Object instantiateItem(View allViews, int position) {
            ((MstarViewPager) allViews).addView(mListView.get(position), 0);
            return mListView.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }

    private class mPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            Log.i(TAG, "----------onPageScrollStateChanged---------- " + arg0);
            if (surfaceView != null) {
                if ((currentpageidx == PAGE2) && (arg0 == PAGE_IDLE)) {
                    surfaceView.setVisibility(View.VISIBLE);
                } else if ((currentpageidx == PAGE2) && (arg0 == PAGE_SLIP)) {
                    surfaceView.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // Log.i(TAG, "----------onPageScrolled----------" + arg0 );
            if ((surfaceView != null) && (currentpageidx == PAGE2)) {
                surfaceView.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageSelected(int position) {
            Log.i(TAG, "----------onPageSelected----------" + position);
            if (position == PAGE2)
                ;
            else {
                if (surfaceView != null) {
                    surfaceView.setVisibility(View.INVISIBLE);
                }
            }
            currentpageidx = position;
            idxImageViews.get(position).setImageResource(R.drawable.focus_idx);
            for (int i = 0; i < idxImageViews.size(); i++) {
                if (position != i) {
                    idxImageViews.get(i).setImageResource(R.drawable.idx);
                }
            }
        }
    }

    private class ItemclickListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ResolveInfo appInfo = (ResolveInfo) parent.getItemAtPosition(position);
            Log.i(TAG, "-----ItemclickListener--------" + appInfo.activityInfo.packageName);
            Intent mIntent = LauncherActivity.this.getPackageManager().getLaunchIntentForPackage(
                    appInfo.activityInfo.packageName);
            if (mIntent != null) {
                changeInputSource(appInfo.activityInfo.packageName);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    LauncherActivity.this.startActivity(mIntent);
                } catch (ActivityNotFoundException anf) {
                    Log.i(TAG,
                            "LauncherActivity.ItemclickListener.onItemClick.ActivityNotFoundException");
                    Toast.makeText(
                            LauncherActivity.this,
                            LauncherActivity.this.getResources().getString(
                                    R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.i(TAG, "LauncherActivity.ItemclickListener.onItemClick.[mIntent != null]");
                Toast.makeText(
                        LauncherActivity.this,
                        LauncherActivity.this.getResources().getString(
                                R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class OnItemSelectPage1 implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // itemidxpage1 = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Log.i(TAG, "----------onNothingSelected----------");
        }
    }

    public class OnItemSelectPage implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // itemidx = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Log.i(TAG, "----------onNothingSelected----------");
        }
    }

    private class AllAppClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            surfaceView.setBackgroundColor(Color.BLACK);
            ComponentName componentName = new ComponentName("com.android.mslauncher",
                    "com.android.mslauncher.AllAppListActivity");
            
            Intent intent = new Intent();
            intent.setComponent(componentName);
            LauncherActivity.this.startActivity(intent);
        }
    }

    public class AppStoreClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            changeInputSource("com.jrm.babao.babaofan");
			/*Xgt*/
			/*
            ComponentName componentName = new ComponentName("com.jrm.babao.babaofan",
                    "com.jrm.babao.babaofan.activity.main.BabaofanMainActivity");
            */
            ComponentName componentName = new ComponentName("com.dangbeimarket",
                            "com.dangbeimarket.activity.WelcomeActivity");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(componentName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            LauncherActivity.this.startActivity(intent);
        }
    }

    public class BrowserClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            changeInputSource("com.android.browser");
            ComponentName componentName = new ComponentName("com.android.browser",
                    "com.android.browser.BrowserActivity");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(componentName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            LauncherActivity.this.startActivity(intent);
        }
    }

    public class OnAppWidgetClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (hostView1 == v) {

                PackageInfo pckInfo = null;
                try {
                    pckInfo = getPackageManager()
                            .getPackageInfo("com.jrm.android.wgt2apk.babao", 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ComponentName componentName = new ComponentName("com.jrm.android.wgt2apk.babao",
                        "com.jrm.android.wgt2apk.babao.Wgt2Apk");

                if (pckInfo == null) {
                    componentName = new ComponentName("com.jrm.android.wgt2apk.babaoDemo",
                            "com.jrm.android.wgt2apk.babaoDemo.Wgt2Apk");
                }

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(componentName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                try {
                    LauncherActivity.this.startActivity(intent);
                    changeInputSource("com.jrm.android.wgt2apk.babao");
                } catch (ActivityNotFoundException anf) {
                    Toast.makeText(
                            LauncherActivity.this,
                            LauncherActivity.this.getResources().getString(
                                    R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
                }
            } else if (hostView2 == v) {
                ComponentName componentName = new ComponentName("com.moji.mjweather",
                        "com.moji.mjweather.CSplashScreen");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(componentName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                try {
                    LauncherActivity.this.startActivity(intent);
                    changeInputSource("com.moji.mjweather");
                } catch (ActivityNotFoundException anf) {
                    Toast.makeText(
                            LauncherActivity.this,
                            LauncherActivity.this.getResources().getString(
                                    R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
                }
            } else if (hostView3 == v) {
                if (WidgetReceiver.getReAppPackageName() != null) {
                    ComponentName componetName = new ComponentName("com.jrm.babao.babaofan",
                            "com.jrm.babao.babaofan.activity.installordetail.BabaofanInstallOrDetailActivity");
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("APP_PACKAGE_FLAG", WidgetReceiver.getReAppPackageName());
                    intent.putExtras(bundle);
                    intent.setComponent(componetName);
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            LauncherActivity.this,
                            LauncherActivity.this.getResources().getString(
                                    R.string.package_not_find_ERROR_2), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class onAppWidgetFocusChangeListener implements View.OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(TAG, "------Widget----focused----------");
                if (hostView1 == v) {
                    hostView1.setBackgroundResource(R.drawable.widgetfocus);
                } else if (hostView2 == v) {
                    hostView2.setBackgroundResource(R.drawable.widgetfocus);
                } else if (hostView3 == v) {
                    hostView3.setBackgroundResource(R.drawable.widgetfocus);
                }
                isWidgetFocus = true;
            } else {
                Log.i(TAG, "------Widget----unfocused----------");
                if (hostView1 == v) {
                    hostView1.setBackgroundResource(R.drawable.widgeticon);
                } else if (hostView2 == v) {
                    hostView2.setBackgroundResource(R.drawable.widgeticon);
                } else if (hostView3 == v) {
                    hostView3.setBackgroundResource(R.drawable.widgeticon);
                }
                isWidgetFocus = false;
            }
        }
    }

    public class onMainItemFocusChangeListener implements View.OnFocusChangeListener {
        private AudioManager audioManager;

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            ++continuousLoadIcon;
            View buttonSettingView = findViewById(R.id.setting);
            View buttonAllappView = findViewById(R.id.allapps);
            View buttonbrowserView = findViewById(R.id.browser);
            View buttonAppstoreView = findViewById(R.id.appstore);
            View buttontvView = findViewById(R.id.tvsetting);
            View buttonmmView = findViewById(R.id.localmm);
            TextView itemText = null;
            if (hasFocus) {
                Log.i(TAG, "------MainItem----focused----------");
                if (buttonSettingView == v) {
                    itemButton = buttonSetting;
                    itemText = (TextView) findViewById(R.id.settingtext);
                } else if (buttonAllappView == v) {
                    itemButton = buttonAllapp;
                    itemText = (TextView) findViewById(R.id.allapstext);
                } else if (buttonbrowserView == v) {
                    itemButton = buttonBrowser;
                    itemText = (TextView) findViewById(R.id.browsertext);
                } else if (buttonAppstoreView == v) {
                    itemButton = buttonAppstore;
                    itemText = (TextView) findViewById(R.id.apstoretext);
                } else if (buttontvView == v) {
                    itemButton = buttonTV;
                    itemText = (TextView) findViewById(R.id.tvsettingtext);
                } else if (buttonmmView == v) {
                    itemButton = buttonMM;
                    itemText = (TextView) findViewById(R.id.localmmtext);
                }
                if (itemText != null) {
                    itemText.setTextColor(Color.rgb(255, 133, 11));
                }
                synchronized (bLoadIcon) {
                    bLoadIcon = true;
                }
                isMainItemsFocus = true;
            } else {
                Log.i(TAG, "-----MainItem-----unfocused----------");
                synchronized (bLoadBreak) {
                    bLoadBreak = true;
                    itemButton = null;
                }
                if (buttonSettingView == v) {
                    buttonSetting.setBackgroundResource(R.drawable.setting00);
                    itemText = (TextView) findViewById(R.id.settingtext);
                } else if (buttonAllappView == v) {
                    buttonAllapp.setBackgroundResource(R.drawable.allapp00);
                    itemText = (TextView) findViewById(R.id.allapstext);
                } else if (buttonbrowserView == v) {
                    buttonBrowser.setBackgroundResource(R.drawable.webbrowser00);
                    itemText = (TextView) findViewById(R.id.browsertext);
                } else if (buttonAppstoreView == v) {
                    buttonAppstore.setBackgroundResource(R.drawable.appstore00);
                    itemText = (TextView) findViewById(R.id.apstoretext);
                } else if (buttontvView == v) {
                    buttonTV.setBackgroundResource(R.drawable.inputsource00);
                    itemText = (TextView) findViewById(R.id.tvsettingtext);
                } else if (buttonmmView == v) {
                    buttonMM.setBackgroundResource(R.drawable.localmm00);
                    itemText = (TextView) findViewById(R.id.localmmtext);
                }
                if (itemText != null) {
                    itemText.setTextColor(Color.rgb(255, 255, 255));
                }
                if (audioManager == null) {
                    audioManager = (AudioManager) LauncherActivity.this
                            .getSystemService(AUDIO_SERVICE);
                }
                audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK);
                isMainItemsFocus = false;
            }
        }
    }

    private int whichApp = -1;

    class Delay2LaunchAppWhenSupernovaNotOkHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what != DISMISS_WAITDIALOG
                    && STR_STATUS_SUSPENDING.equals(SystemProperties.get("mstar.str.suspending",
                            STR_STATUS_NONE))) {
                getWaitDialog().show();
                whichApp = msg.what;
                return;
            }
            Intent intent = null;
            ComponentName componentName = null;
            switch (msg.what) {
                case DISMISS_WAITDIALOG:
                    getWaitDialog().dismiss();
                    break;
                case GO_TO_SETTING:
                    changeInputSource("com.android.settings");
                    componentName = new ComponentName("com.android.settings",
                            "com.android.settings.Settings");
                    intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(componentName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    LauncherActivity.this.startActivity(intent);
                    break;

                case GO_TO_TVAPK:
                    Log.d(TAG, "Click Tv Image Flag = " + hasTVClick);
                    if ((ThirdPartyDtvValue == 0) && (hasTVClick == false)) {
                        Log.d(TAG, "Start execute TV APK ~ ");
                        hasTVClick = true;
                        surfaceView.setBackgroundColor(Color.BLACK);
                        changeInputSource("com.mstar.tv.tvplayer.ui");
                        componentName = new ComponentName("com.mstar.tv.tvplayer.ui",
                                "com.mstar.tv.tvplayer.ui.RootActivity");
                        intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
                        intent.setComponent(componentName);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        LauncherActivity.this.startActivity(intent);
                        Log.d(TAG, "End execute TV APK ~ ");
                    }
                    break;

                case GO_TO_MM:
                    changeInputSource("com.mstar.localmm");
					
                    componentName = new ComponentName("com.jrm.localmm",
                            "com.jrm.localmm.ui.main.FileBrowserActivity");
                    /*
                    componentName = new ComponentName("com.dangbeimarket",
                            "com.dangbeimarket.activity.WelcomeActivity");
                    */
                    intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(componentName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    LauncherActivity.this.startActivity(intent);
                    break;
                case SET_WIN_SCALE:
                    setPipscale();
                    if (surfaceView != null)
                        surfaceView.setBackgroundColor(Color.TRANSPARENT);
                    break;
                default:
                    break;
            }
        }
    }

    public class SettingClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            delay2LaunchAppWhenSupernovaNotOkHandler.sendEmptyMessage(GO_TO_SETTING);
        }
    }

    public class TVSourceClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            delay2LaunchAppWhenSupernovaNotOkHandler.sendEmptyMessage(GO_TO_TVAPK);
        }
    }

    public class LocalMMClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            delay2LaunchAppWhenSupernovaNotOkHandler.sendEmptyMessage(GO_TO_MM);
        }
    }

    public class webContentClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            View newsView = findViewById(R.id.buttonzixun);
            View weatherView = findViewById(R.id.buttontianqi);
            View apRecomandView = findViewById(R.id.buttonyinyong);
            View commonInfoView = findViewById(R.id.buttonxinxi);
            Intent intent = new Intent();
            if (commonInfoView == v) {
                Log.i(TAG, "Enter to webContentClickListener.onClick.[commonInfoView == v]...");
                if (Tools.isBox()) {
                    try {
                        Log.i(TAG,
                                "Enter to webContentClickListener.onClick.[commonInfoView == v]-->try...");
                        ComponentName componetName = new ComponentName("com.pocketdigi.dayday",
                                "com.pocketdigi.dayday.Main");
                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
                        intent.setComponent(componetName);
                        LauncherActivity.this.startActivity(intent);
                        changeInputSource("com.pocketdigi.dayday");
                    } catch (ActivityNotFoundException anf) {
                        Log.i(TAG,
                                "Enter to webContentClickListener.onClick.[commonInfoView == v]-->catch...");
                        ComponentName componetName = new ComponentName("com.jrm.babao.babaofan",
                                "com.jrm.babao.babaofan.activity.installordetail.BabaofanInstallOrDetailActivity");
                        Bundle bundle = new Bundle();
                        bundle.putString("APP_PACKAGE_FLAG", "com.pocketdigi.dayday");
                        intent.putExtras(bundle);
                        intent.setComponent(componetName);

                        try {
                            LauncherActivity.this.startActivity(intent);
                            changeInputSource("com.jrm.babao.babaofan");
                        } catch (ActivityNotFoundException e) {
                            Log.i(TAG,
                                    "Enter to webContentClickListener.onClick.[commonInfoView == v]-->catch error...");
                            Toast.makeText(
                                    LauncherActivity.this,
                                    LauncherActivity.this.getResources().getString(
                                            R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                } else {
                    Toast.makeText(
                            LauncherActivity.this,
                            LauncherActivity.this.getResources().getString(
                                    R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public GridPageAdapter getGPageAdapter(String packageName) {
        for (int i = 0; i < PAGE_APP_NUM; i++) {
            if (i < mGridPageAdapter1.getCount()
                    && packageName
                            .equals("package:"
                                    + ((ResolveInfo) (mGridPageAdapter1.getItem(i))).activityInfo.packageName)) {
                return mGridPageAdapter1;
            } else if (i < mGridPageAdapter2.getCount()
                    && packageName
                            .equals("package:"
                                    + ((ResolveInfo) (mGridPageAdapter2.getItem(i))).activityInfo.packageName)) {
                return mGridPageAdapter2;
            } else if (i < mGridPageAdapter3.getCount()
                    && packageName
                            .equals("package:"
                                    + ((ResolveInfo) (mGridPageAdapter3.getItem(i))).activityInfo.packageName)) {
                return mGridPageAdapter3;
            }
        }
        return null;
    }

    // /////////////tvsurface///////////////////
    private void openSurfaceView() {
        final SurfaceHolder mSurfaceHolder = surfaceView.getHolder();
        // surfaceParams = new LayoutParams();
        // surfaceParams.x = dimens2px(R.integer.surfaceParams_x);
        // surfaceParams.y = dimens2px(R.integer.surfaceParams_y);
        // surfaceParams.width = dimens2px(R.integer.surfaceParams_width);
        // surfaceParams.height = dimens2px(R.integer.surfaceParams_height);
        // surfaceParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;//
        // WindowManager.LayoutParams.TYPE_APPLICATION
        // // ;//WindowManager.LayoutParams.TYPE_WALLPAPER;
        // surfaceParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        // | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        // Log.v(TAG, "openSurfaceView===" + surfaceParams);
        // surfaceParams.gravity = Gravity.TOP | Gravity.LEFT;
        callback = new android.view.SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.v(TAG, "===surfaceDestroyed===");
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (holder == null || holder.getSurface() == null
                            || holder.getSurface().isValid() == false)
                        return;
                    Log.v(TAG, "===surfaceCreated===");
                    if (TvManager.getInstance() != null) {
                        TvManager.getInstance().getPlayerManager().setDisplay(mSurfaceHolder);
                    }
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.v(TAG, "===surfaceChanged===");
                createsurface = true;
            }
        };
        mSurfaceHolder.addCallback(callback);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        // wm = (WindowManager) getApplicationContext().getSystemService(
        // WINDOW_SERVICE);
        // wm.removeView(surfaceView);
        // wm.addView(surfaceView, surfaceParams);
    }

    private void setFullscale() {
        try {
            VideoWindowType videoWindowType = new VideoWindowType();
            videoWindowType.height = 0xffff;
            videoWindowType.width = 0xffff;
            videoWindowType.x = 0xffff;
            videoWindowType.y = 0xffff;
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager()
                        .selectWindow(EnumScalerWindow.E_MAIN_WINDOW);
                TvManager.getInstance().getPictureManager().setDisplayWindow(videoWindowType);
                TvManager.getInstance().getPictureManager().scaleWindow();
            }
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "setFullscale");
    }

    private int getTVLeftOffset(int x) {
        // y = 24/99x + 75/99
        return (int) (24 / 99.0 * x + 75 / 99.0);
    }

    private int getTVRigthOffset(int x) {
        return (x / 10);
    }

    private void setPipscale() {
        try {
            if (STR_STATUS_SUSPENDING.equals(SystemProperties.get("mstar.lvds-off", "1"))) {
                Log.i(TAG, "power off!!!");
            } else {
                VideoWindowType videoWindowType = new VideoWindowType();

                if (Tools.isBox()) {
                    PictureManager pm = TvManager.getInstance().getPictureManager();
                    PanelProperty pp = pm.getPanelWidthHeight();

                    ReproduceRate rr = new ReproduceRate(pm.getReproduceRate());
                    // Log.i(TAG, " ??????????????? ReproduceRate rr top : " +
                    // rr._reduceRate.topRate +
                    // "  bottom : "+rr._reduceRate.bottomRate +
                    // "  left : "+rr._reduceRate.leftRate+"  right : "+rr._reduceRate.rightRate);

                    float scale_w = (W_1920 / pp.width);
                    float scale_h = (H_1080 / pp.height);
                    int lOffset = getTVLeftOffset(rr._reduceRate.leftRate);
                    int rOffset = getTVRigthOffset(rr._reduceRate.rightRate);
                    int tOffset = getTVLeftOffset(rr._reduceRate.topRate);
                    // Log.i(TAG,
                    // " ??????????????? PanelProperty  width : "+pp.width+"  height : "+pp.height);
                    // Log.i(TAG,
                    // " ??????????????? w_1920 / pp.width : "+scale_w
                    // +"   scale_h / pp.height : "+scale_h);
                    // Log.i(TAG, " ??????????????? offset : "+lOffset);
                    videoWindowType.x = (int) ((getResources().getInteger(
                            R.integer.videoWindowType_x)
                            + rr._reduceRate.leftRate - lOffset - rOffset) / scale_w);
                    videoWindowType.y = (int) ((getResources().getInteger(
                            R.integer.videoWindowType_y)
                            + rr._reduceRate.topRate - tOffset) / scale_h);
                    videoWindowType.width = (int) (getResources().getInteger(
                            R.integer.videoWindowType_width) / scale_w);
                    videoWindowType.height = (int) (getResources().getInteger(
                            R.integer.videoWindowType_height) / scale_h);
                } else {
                    videoWindowType.x = dimens2px(R.integer.videoWindowType_dimen_x);
                    videoWindowType.y = dimens2px(R.integer.videoWindowType_dimen_y);
                    videoWindowType.height = dimens2px(R.integer.videoWindowType_dimen_height);
                    videoWindowType.width = dimens2px(R.integer.videoWindowType_dimen_width);
                }

                if (TvManager.getInstance() != null) {
                    if (TvManager.getInstance().getPictureManager() != null) {
                        TvManager.getInstance().getPictureManager()
                                .selectWindow(EnumScalerWindow.E_MAIN_WINDOW);
                    }
                }
                if (TvManager.getInstance() != null) {
                    if (TvManager.getInstance().getPictureManager() != null) {
                        Log.i(TAG, " ??????????????? x : " + videoWindowType.x + "   y : "
                                + videoWindowType.y + "       w : " + videoWindowType.width
                                + "       h : " + videoWindowType.height);
                        TvManager.getInstance().getPictureManager()
                                .setDisplayWindow(videoWindowType);
                    }
                }
                if (TvManager.getInstance() != null) {
                    if (TvManager.getInstance().getPictureManager() != null) {
                        TvManager.getInstance().getPictureManager().scaleWindow();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "***************>>>>>>>>>>>>>setPipscale");
    }

    public void changeInputSource(String packName) {
        Log.i(TAG, "changeInputSource------------" + packName);
        if (packName != null) {
            if (packName.contentEquals("com.mstar.tv.tvplayer.ui")
                    || packName.contentEquals("mstar.factorymenu.ui")
                    || packName.contentEquals("com.tvos.pip")
                    || packName.contentEquals("com.mstar.tvsetting.hotkey")
                    || packName.contentEquals("com.babao.tvju")
                    || packName.contentEquals("com.babao.socialtv")
                    || packName.contentEquals("com.mstar.appdemo")) {
                Log.i(TAG, "------------TV AP");
                 synchronized (bSync) {
                        fullScale = SCALE_FULL;
                    }
            } else {
                synchronized (bSync) {
                    if (STR_STATUS_SUSPENDING.equals(SystemProperties.get("mstar.str.suspending",
                            "0"))) {
                        SystemProperties.set("mstar.str.storage", "1");
                    }
                    toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_STORAGE;
                }
            }
        }
    }

    public void BackHomeSource() {
        synchronized (bSync) {
            if (this.getEnablePipFlagIndex() == 0) {
                toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_ATV;
            } else {
                toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_STORAGE;
            }
        }
    }

    private void setDefaultWallpaper() {
        if (!mWallpaperChecked) {
            Drawable wallpaper = peekWallpaper();
            if (wallpaper == null) {
                try {
                    clearWallpaper();
                } catch (IOException e) {
                    Log.e(TAG, "Failed to clear wallpaper " + e);
                }
            } else {
                getWindow().setBackgroundDrawable(new ClippedDrawable(wallpaper));
            }
            mWallpaperChecked = true;
        }
    }

    private int getEnablePipFlagIndex() {
        SharedPreferences pre = getSharedPreferences("temp_sms", MODE_WORLD_READABLE);
        String index = pre.getString("sms_content", "");
        Log.d(TAG, "   --> : getEnablePipFlagIndex : " + index);
        return isParserNumber(index);
    }

    private void setEnablePipFlagIndex(int enablePipFlagIndex) {
        Log.d(TAG, "   --> : setEnablePipFlagIndex : " + enablePipFlagIndex);
        SharedPreferences.Editor editor = getSharedPreferences("temp_sms", MODE_WORLD_WRITEABLE).edit();
        editor.putString("sms_content", String.valueOf(enablePipFlagIndex));
        editor.commit();
    }

    private int isParserNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    private class ClippedDrawable extends Drawable {

        private final Drawable mWallpaper;

        public ClippedDrawable(Drawable wallpaper) {
            mWallpaper = wallpaper;
        }

        @Override
        public void setBounds(int left, int top, int right, int bottom) {
            super.setBounds(left, top, right, bottom);
            mWallpaper.setBounds(left, top, left + mWallpaper.getIntrinsicWidth(),
                    top + mWallpaper.getIntrinsicHeight());
        }

        @Override
        public void draw(Canvas canvas) {
            mWallpaper.draw(canvas);
        }

        @Override
        public void setAlpha(int alpha) {
            mWallpaper.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
            mWallpaper.setColorFilter(cf);
        }

        @Override
        public int getOpacity() {
            return mWallpaper.getOpacity();
        }
    }

    private void registerIntentReceivers() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_WALLPAPER_CHANGED);
        registerReceiver(mWallpaperReceiver, filter);
    }

    private class WallpaperIntentReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            updateWallpaperVisibility(true);
        }
    }

    private void startWallpaper() {
        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
        startActivity(Intent.createChooser(pickWallpaper, getString(R.string.wallpaper)));
    }

    void updateWallpaperVisibility(boolean visible) {
        int wpflags = visible ? WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER : 0;
        int curflags = getWindow().getAttributes().flags
                & WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER;
        Log.d(TAG, "   ---   updateWallpaperVisibility : " + visible + ", " + curflags + ", " + curflags);
        if (wpflags != curflags) {
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
                WallpaperManager.getInstance(this).suggestDesiredDimensions(metrics.widthPixels,
                        metrics.heightPixels);
            getWindow().setFlags(wpflags, WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER);
        }
    }

    void updateWallpaperVisibilityForResetWallPaper(boolean visible) {
        int wpflags = visible ? WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER : 0;
        int curflags = getWindow().getAttributes().flags
                & WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER;
        Log.d(TAG, "   ---   updateWallpaperVisibilityforResetWallPaper : " + visible + ", " + curflags + ", " + curflags);
        if (wpflags != curflags) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

            Log.d(TAG, "   ---   updateWallpaperVisibilityforResetWallPaper : " + metrics.widthPixels + ", " + metrics.heightPixels);
            WallpaperManager.getInstance(this).suggestDesiredDimensions(metrics.widthPixels,metrics.heightPixels);
            getWindow().setFlags(wpflags, WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER);
        }
    }

    public int addItem(ResolveInfo rsi) {
        if (mGridPageAdapter1.findItem(rsi)) {
            return TOTALICON + PAGE2 + 1;
        }
        if (mGridPageAdapter2.findItem(rsi)) {
            return TOTALICON + PAGE1 + 1;
        }
        if (mGridPageAdapter3.findItem(rsi)) {
            return TOTALICON + PAGE3 + 1;
        }
        if (mGridPageAdapter1.getCount() == 10 && mGridPageAdapter2.getCount() == TOTALICON
                && mGridPageAdapter3.getCount() == TOTALICON) {
            return TOTALICON;
        }
        if (mGridPageAdapter1.getCount() < 10) {
            mGridPageAdapter1.addItemIcon(rsi, PAGE2 + 1);
            return PAGE2;
        } else if (mGridPageAdapter2.getCount() < TOTALICON) {
            mGridPageAdapter2.addItemIcon(rsi, PAGE1 + 1);
            return PAGE1;
        } else if (mGridPageAdapter3.getCount() < TOTALICON) {
            mGridPageAdapter3.addItemIcon(rsi, PAGE3 + 1);
            return PAGE3;
        } else {
            return -1;
        }
    }

    /**
     * Implementation of the method from SysShutDownReceiver.Callbacks.
     */
    public void setSysShutdown() {
        Log.v(TAG, "------------setSysShutdown=");
        bSystemShutdown = true;
    }

    class FileFlyReceiver extends BroadcastReceiver {

        public FileFlyReceiver() {
            Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>  this is box");
        }

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>  this is box receive com.jrm.filefly.action");
            String action = arg1.getAction();
            if (FILE_FLY_LAUNCH.equals(action)) {
                synchronized (bSync) {
                    if (STR_STATUS_SUSPENDING.equals(SystemProperties.get("mstar.str.suspending",
                            "0"))) {
                        SystemProperties.set("mstar.str.storage", "1");
                    }
                    toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_STORAGE;
                }
            }
        }
    }

    private Handler scaleWinHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SCALE_FULL:
                    synchronized (bSync) {
                        fullScale = SCALE_FULL;
                    }
                    break;
                case SCALE_SMALL:
                    synchronized (bSync) {
                        fullScale = SCALE_SMALL;
                    }

                    handlertv.postDelayed(pip_thread, 500); // delay to show tv
                                                            // window, wait
                                                            // launcher UI.
                    surfaceView.setBackgroundColor(Color.TRANSPARENT);
                    break;
            }
        }
    };

    public boolean isPowerOn() {
        Log.d(TAG, "Is Fist Power On: " + (SystemProperties.getBoolean(IS_POWER_ON_PROPERTY, false)));
        if (!SystemProperties.getBoolean(IS_POWER_ON_PROPERTY, false)) {
            SystemProperties.set(IS_POWER_ON_PROPERTY, "true");
            return true;
        } else {
            return false;
        }
    }

    private static boolean prevWifiConnected = false;
    private boolean mWifiConnected = false;
    //ljq add
    private class UpdateUIBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            String path = intent.getDataString();

            if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {

                if(path.contains("/usb")) {
                    buttonxinxi.setVisibility(View.VISIBLE);
                }

            } else if (action.equals(Intent.ACTION_MEDIA_EJECT)) {
                buttonxinxi.setVisibility(View.INVISIBLE);

            }else if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                Log.d(TAG, "onReceive: wifi状态改变");
                final NetworkInfo networkInfo = (NetworkInfo) intent
                        .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                boolean wifiConnected = networkInfo != null && networkInfo.isConnected();
                if (prevWifiConnected != wifiConnected) {

                    if (wifiConnected) {
                       
                        mWifiConnected = true;
                    } else {

                        mWifiConnected = false;
                    }


                } else {

                }
                prevWifiConnected = wifiConnected;
            }

        }
    }
    private class wifiBrocasRceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String path = intent.getDataString();
            if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
                final NetworkInfo networkInfo = (NetworkInfo) intent
                        .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                boolean wifiConnected = networkInfo != null && networkInfo.isConnected();
                if (prevWifiConnected != wifiConnected) {

                    if (wifiConnected) {

                        mWifiConnected = true;
                    } else {

                        mWifiConnected = false;
                    }


                } else {

                }
                prevWifiConnected = wifiConnected;
            }
        }
    }

    class ShowTimeHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case msgKey1:
                    long sysTime = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                    Date date = new Date(sysTime);

                    String dates = sdf.format(date);
                    textViewTime.setText(dates);
                    break;

                default:
                    break;
            }
        }
    }

    class ShowTimeThread implements Runnable{

        @Override
        public void run() {

            while (isgetTime){

                try {
                    Message message = new Message();
                    message.what = msgKey1;
                    showTimeHandler.sendMessage(message);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    //ljq add end


}
