
package com.android.mslauncher;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mstar.android.tv.TvChannelManager;
import com.mstar.android.tv.TvCommonManager;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceInputType;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceType;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.tvframework.Agent;
import com.mstar.tvframework.Entity;
import com.mstar.tvframework.MEventHandler;
import com.mstar.tvframework.MGameEvent;
import com.mstar.tvframework.MUpdateHandler;
import com.mstar.tvframework.Mstar3DUIActivity;
import com.mstar.tvframework.MstarOKitUI;

public class Launcher3DActivity extends Mstar3DUIActivity {

    private static final String TAG = "Launcher3DActivity";

    private PageIcon3DAdapter pageIcon = null;

    public static Context mContext;

    public boolean moveMode = false;

    // private AppWidget3DHostView app3dwidget = null;

    private AppWidgetHost mAppWidgetHost;

    private AppWidgetManager mAppWidgetManager;

    private static final int APPWIDGET_HOST_ID = 1024;

    private AppWidgetProviderInfo appWidgetInfo = null;

    private int appWidgetId;

    AppWidgetHostView hostView;

    private LinearLayout.LayoutParams linearParams;

    public Bitmap widgetBitmap = null;

    private EnumInputSource toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_NONE;

    private InputSourceThread inputSourceThread = new InputSourceThread();

    private Boolean bSync = true;

    private Boolean bExitThread = false;

    private TvCommonManager commonService;

    private TvChannelManager tvChannelManager;

    private boolean reDraw = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = this;

        blendFile = getAssetsFiles("MStarAnHome.blend", "MStarAnHome.btmp");
        configFile = getAssetsFiles("MStarAnHome.cfg", "MStarAnHome.ctmp");
        agentFile = getAssetsFiles("Agent.xml", "Agent.atmp");

        super.onCreate(savedInstanceState);
        commonService = TvCommonManager.getInstance();
        tvChannelManager = TvChannelManager.getInstance();
        setContentView(R.layout.main3d);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main3dlayout);
        linearLayout.setBackgroundResource(R.drawable.background3d);
        linearLayout.addView(glView);

        initAppWidget(linearLayout);
        Agent agent = MstarOKitUI.getInstance().getAgentManager()
                .getAgent(getString(R.string.my_app_agent));
        ArrayList<Entity> entityList = agent.getEntitys();
        pageIcon = new PageIcon3DAdapter(Launcher3DActivity.this, entityList);

        // bind tv handler
        TVEventHandler tvEventHandler = new TVEventHandler();
        MstarOKitUI.getInstance().bind(tvEventHandler, getString(R.string.show_agent));

        MovieEventHandler movieEventHandler = new MovieEventHandler();
        MstarOKitUI.getInstance().bind(movieEventHandler, getString(R.string.rec_video_agent));
        // bind Pre-App handler
        PreAppUpdateHandler preAppUpdateHandler = new PreAppUpdateHandler();
        MstarOKitUI.getInstance().bind(preAppUpdateHandler, getString(R.string.pre_app_agent));
        PreAppEventHandler preAppEventHandler = new PreAppEventHandler();
        MstarOKitUI.getInstance().bind(preAppEventHandler, getString(R.string.pre_app_agent));
        // bind favorit handler
        FavoriteUpdateHandler favoriteUpdateHandler = new FavoriteUpdateHandler();
        MstarOKitUI.getInstance().bind(favoriteUpdateHandler, getString(R.string.favorite_agent));
        FavoriteEventHandler favoriteEventHandler = new FavoriteEventHandler();
        MstarOKitUI.getInstance().bind(favoriteEventHandler, getString(R.string.favorite_agent));
        // bind my-app handler
        MyAppUpdateHandler myAppUpdateHandler = new MyAppUpdateHandler();
        MstarOKitUI.getInstance().bind(myAppUpdateHandler, getString(R.string.my_app_agent));
        MyAppEventHandler myAppEventHandler = new MyAppEventHandler();
        MstarOKitUI.getInstance().bind(myAppEventHandler, getString(R.string.my_app_agent));

        // bind my-app handler
        WidgetUpdateHandler widgetUpdateHandler = new WidgetUpdateHandler();
        MstarOKitUI.getInstance().bind(widgetUpdateHandler, getString(R.string.rec_app_agent));
        WidgetEventHandler widgetEventHandler = new WidgetEventHandler();
        MstarOKitUI.getInstance().bind(widgetEventHandler, getString(R.string.rec_app_agent));

        bExitThread = false;
        new Thread(inputSourceThread).start();
        // end
    }

    private void initAppWidget(LinearLayout linearLayout) {
        mAppWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        mAppWidgetHost = new AppWidget3DHost(getApplicationContext(), APPWIDGET_HOST_ID);
        mAppWidgetHost.startListening();

        ArrayList<AppWidgetProviderInfo> mAppwidgetProviderInfos = new ArrayList<AppWidgetProviderInfo>();
        mAppwidgetProviderInfos = (ArrayList<AppWidgetProviderInfo>) mAppWidgetManager
                .getInstalledProviders();
        for (int i = 0; i < mAppwidgetProviderInfos.size(); i++) {
            if (mAppwidgetProviderInfos.get(i).label.equalsIgnoreCase("RecommendApp")) {
                appWidgetInfo = mAppwidgetProviderInfos.get(i);
            }
        }

        if (appWidgetInfo != null) {
            ComponentName cn = new ComponentName("com.mstar.appwidget",
                    "com.mstar.appwidget.ReAppWidgetProvider");
            appWidgetId = mAppWidgetHost.allocateAppWidgetId();

            mAppWidgetManager.bindAppWidgetId(appWidgetId, cn);
            appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(appWidgetId);
            hostView = mAppWidgetHost.createView(this, appWidgetId, appWidgetInfo);
            linearParams = new LinearLayout.LayoutParams(getResources().getInteger(
                    R.integer.Widget_3dwidth), getResources().getInteger(R.integer.Widget_3dheight));
            hostView.setFocusable(true);

            hostView.setPadding(0, 0, 0, 0);
            ((ViewGroup) linearLayout).addView(hostView, linearParams);
        }
    }

    public String getAssetsFiles(String fileName, String tmpName) {
        String blenderPath = getFilesDir() + "/" + fileName;
        Log.i(TAG, blenderPath + " " + MstarUtil.getInstance().isExist(blenderPath));
        if (!MstarUtil.getInstance().isExist(blenderPath)) {
            AssetManager am = this.getAssets();
            try {
                InputStream in = am.open(fileName);
                int length = in.available();
                Log.v(TAG, "*********length = ********" + length);
                byte[] buff = new byte[length];
                in.read(buff);
                FileOutputStream out = openFileOutput(tmpName, Activity.MODE_PRIVATE);
                out.write(buff);
                out.flush();
                out.close();
                Runtime r = Runtime.getRuntime();
                Process p = r.exec("sync \r");
                String Command = "mv /data/data/com.android.mslauncher/files/" + tmpName + " "
                        + blenderPath;
                Log.v(TAG, "*********" + Command);
                p = r.exec(Command);
                if (p == null) {
                    Log.v(TAG, "*********copy file fail ********");
                }
                r.exec("sync \r");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return blenderPath;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.i(TAG, "+++++++++++++++++++++down+++++++++++++++++++++");

        // if(keyCode== KeyEvent.KEYCODE_MENU)
        // startWallpaper();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.i(TAG, "+++++++++++++++++++++up+++++++++++++++++++++");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "----------onRestart----------");

        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "----------onPause----------");
        super.onPause();
    }

    Handler handlerAllIcon = new Handler();

    Runnable AllIconDraw = new Runnable() {

        @Override
        public void run() {
            Agent agent = MstarOKitUI.getInstance().getAgentManager()
                    .getAgent(getString(R.string.my_app_agent));
            ArrayList<Entity> entityList = agent.getEntitys();
            for (int i = 0; i < entityList.size(); i++) {
                MstarOKitUI.getInstance().update(agent.getAgentName(), entityList.get(i).getName());
            }

            reDraw = false;
            handlerAllIcon.removeCallbacks(AllIconDraw);
        }
    };

    @Override
    protected void onResume() {
        Log.i(TAG, "<<<<<<----------enter onResume---------->>>>>");
        MstarOKitUI.getInstance().startVECapture();
        if (reDraw == true) {
            handlerAllIcon.postDelayed(AllIconDraw, 50);
        }
        BackHomeSource();
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "----------onStop----------");
        MstarOKitUI.getInstance().endVECapture();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "----------onDestroy----------");
        super.onDestroy();
        bExitThread = true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void callBackInitDone(String sceneName) {
        Log.v(TAG, "++++++++++++Launcher3DActivity:callbackInitDone");

        for (int i = 0; i < pageIcon.msList.size(); i++) {
            MstarOKitUI.getInstance().update(pageIcon.msList.get(i).getAgent(),
                    pageIcon.msList.get(i).getObject());
        }

        MstarOKitUI.getInstance().prepareVECapture();
        MstarOKitUI.getInstance().bindVECapture("Show", "Page2.000");
    }

    public boolean addIcon(String title, String packageName, String className) {
        boolean addResult = false;
        Agent agent = MstarOKitUI.getInstance().getAgentManager()
                .getAgent(getString(R.string.my_app_agent));

        if (pageIcon.removeEntityList.size() > 0) {
            Log.i(TAG, "----->remove list is not null-------");
            for (int i = 0; i < pageIcon.msList.size(); i++) {
                if (pageIcon.msList.get(i).getClassName().equalsIgnoreCase(className)
                        && pageIcon.msList.get(i).getAgent().equalsIgnoreCase(agent.getAgentName())) {
                    return addResult;
                }
            }

            addResult = pageIcon.addIcon(agent.getAgentName(),
                    pageIcon.removeEntityList.get(pageIcon.getRemoveListSize() - 1), title,
                    packageName, className);
            MstarOKitUI.getInstance().update(agent.getAgentName(),
                    pageIcon.removeEntityList.get(pageIcon.getRemoveListSize() - 1));
            pageIcon.removeEntityList.remove(pageIcon.getRemoveListSize() - 1);
        } else {

            int index = pageIcon.getEntityNum(agent, className);
            Log.i(TAG, "----->the index is-------" + index);
            if (index == -1) {
                return addResult;
            }
            ArrayList<Entity> entityList = agent.getEntitys();
            if (index < entityList.size()) {
                addResult = pageIcon.addIcon(agent.getAgentName(), entityList.get(index).getName(),
                        title, packageName, className);
            }
            MstarOKitUI.getInstance().update(agent.getAgentName(), entityList.get(index).getName());
        }

        return addResult;
    }

    public void delUpdateIcon(String entityName) {
        if (entityName == null) {
            return;
        }
        Agent agent = MstarOKitUI.getInstance().getAgentManager()
                .getAgent(getString(R.string.my_app_agent));

        ArrayList<Entity> entityList = agent.getEntitys();
        for (int j = 0; j < entityList.size(); j++) {
            MstarOKitUI.getInstance().update(agent.getAgentName(), entityList.get(j).getName());
        }

    }

    public void deleteIcon(final String entityName, final String agentName) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(getResources().getString(R.string.deltitle));
        ad.setMessage(getResources().getString(R.string.delmessage));
        ad.setPositiveButton(getResources().getString(R.string.delconfirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        pageIcon.removeIcon(entityName, agentName);
                        delUpdateIcon(entityName);
                    }
                });
        ad.setNegativeButton(getResources().getString(R.string.delcancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });
        ad.show();
    }

    public void deleteIcon(String packageName) {
        if (packageName != null) {
            String subPackage = packageName.substring(8);

            Log.i(TAG, "------->call deleteIcon<------" + subPackage);

            String entityName = pageIcon.getEntityName(subPackage);
            String agentName = pageIcon.getAgentName(subPackage);

            Log.i(TAG, "---entityName---" + entityName + "------------agentName:" + agentName);
            if (entityName != null && agentName != null) {
                pageIcon.removeIcon(entityName, agentName);
            }
        }

        reDraw = true;

    }

    class InputSourceThread implements Runnable {
        @Override
        public void run() {

            EnumInputSource tmpInputSource;

            while (!bExitThread) {

                synchronized (bSync) {
                    tmpInputSource = toChangeInputSource;
                    toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_NONE;
                }

                if (tmpInputSource != EnumInputSource.E_INPUT_SOURCE_NONE) {

                    Log.v(TAG, "\n  change source=====" + tmpInputSource);

                    // ITvServiceServer tvService = ITvServiceServer.Stub

                    // .asInterface(ServiceManager

                    // .checkService(Context.TV_SERVICE));

                    // if (tvService == null) {

                    // Log.w(TAG, "Unable to find ITvService interface.");

                    // } else {

                    EnumInputSource currentSource;
                    // ITvServiceServerCommon commonService =
                    // tvService.getCommonManager();

                    if (tmpInputSource == EnumInputSource.E_INPUT_SOURCE_ATV) {
                        currentSource = commonService.getCurrentInputSource();
                        if (currentSource.equals(EnumInputSource.E_INPUT_SOURCE_STORAGE)) {
                            int curSource = commonService.getPowerOnSource().ordinal();
                            Log.v(TAG, "\n  cursource---->" + curSource);
                            if ((curSource >= 0)
                                    && (curSource <= EnumInputSource.E_INPUT_SOURCE_NONE.ordinal())) {
                                commonService.setInputSource(EnumInputSource.values()[curSource]);
                                if (EnumInputSource.values()[curSource] == EnumInputSource.E_INPUT_SOURCE_ATV) {
                                    int channel = tvChannelManager.getCurrentChannelNumber();
                                    if ((channel < 0) || (channel > 255)) {
                                        channel = 0;
                                    }
                                    // tvService.getChannelManager().programSel(channel,
                                    // EN_MEMBER_SERVICE_TYPE.E_SERVICETYPE_ATV);
                                } else if (EnumInputSource.values()[curSource] == EnumInputSource.E_INPUT_SOURCE_DTV) {
                                    tvChannelManager.changeToFirstService(
                                            EnumFirstServiceInputType.E_FIRST_SERVICE_DTV,
                                            EnumFirstServiceType.E_DEFAULT);
                                }
                            }
                        }

                    } else {

                        commonService.setInputSource(tmpInputSource);
                    }

                    // }
                } else {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

    }

    public void changeInputSource(String packName) {

        if (packName != null) {

            if (packName.contentEquals("com.mstar.tv.tvplayer.ui")

            || packName.contentEquals("mstar.factorymenu.ui")

            || packName.contentEquals("com.tvos.pip")

            || packName.contentEquals("com.mstar.tvsetting.hotkey")

            || packName.contentEquals("com.android.settings")) {

                Log.i(TAG, "------------TV AP");

            } else {

                synchronized (bSync) {
                    toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_STORAGE;
                }

            }

        }
    }

    public void BackHomeSource() {

        synchronized (bSync) {
            toChangeInputSource = EnumInputSource.E_INPUT_SOURCE_ATV;
        }

    }

    // ////////////////////////////all
    // handler///////////////////////////////////
    // tv handler
    class TVEventHandler extends MEventHandler {

        @Override
        public void OnInit(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnClick(MGameEvent event) {
            // TODO Auto-generated method stub
            if (event.entityName.equalsIgnoreCase(getResources().getString(R.string.show_tv))) {
                ComponentName componentName = new ComponentName("com.mstar.tv.tvplayer.ui",
                        "com.mstar.tv.tvplayer.ui.RootActivity");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(componentName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                try {
                    Launcher3DActivity.this.startActivity(intent);

                } catch (ActivityNotFoundException anf) {
                    Toast.makeText(
                            Launcher3DActivity.this,
                            Launcher3DActivity.this.getResources().getString(
                                    R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
                }
            }

        }

        @Override
        public void OnSelect(MGameEvent event) {
            // TODO Auto-generated method stub
            Log.v(TAG, "+++++++++++++++++++MLauncher3DEventHandler was invoked evetn.agentName="
                    + event.agentName + ",event.entityName=" + event.entityName);
            if (event.entityName.equalsIgnoreCase(getResources().getString(R.string.show_tv))) {
                ComponentName componentName = new ComponentName("com.mstar.tv.tvplayer.ui",
                        "com.mstar.tv.tvplayer.ui.RootActivity");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(componentName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                try {
                    Launcher3DActivity.this.startActivity(intent);

                } catch (ActivityNotFoundException anf) {
                    Toast.makeText(
                            Launcher3DActivity.this,
                            Launcher3DActivity.this.getResources().getString(
                                    R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
                }
            }

        }

        @Override
        public void OnKey(MGameEvent event) {
            Log.i(TAG, "------>MyApp onKey:" + event.keyCode + "agent:" + event.agentName
                    + " entity:" + event.entityName);

        }

        @Override
        public void OnMouseOver(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnFocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnUnfocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnSwapEntity(MGameEvent event) {

        }
    }

    // movie handler
    class MovieEventHandler extends MEventHandler {

        @Override
        public void OnInit(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnClick(MGameEvent event) {
            // TODO Auto-generated method stub
            ComponentName componentName = new ComponentName("com.jrm.localmm",
                    "com.jrm.localmm.ui.main.FileBrowserActivity");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(componentName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            try {
                Launcher3DActivity.this.startActivity(intent);

            } catch (ActivityNotFoundException anf) {
                Toast.makeText(
                        Launcher3DActivity.this,
                        Launcher3DActivity.this.getResources().getString(
                                R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void OnSelect(MGameEvent event) {
            // TODO Auto-generated method stub
            Log.v(TAG, "+++++++++++++++++++MLauncher3DEventHandler was invoked evetn.agentName="
                    + event.agentName + ",event.entityName=" + event.entityName);
            ComponentName componentName = new ComponentName("com.jrm.localmm",
                    "com.jrm.localmm.ui.main.FileBrowserActivity");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(componentName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            try {
                changeInputSource("com.jrm.localmm");
                Launcher3DActivity.this.startActivity(intent);

            } catch (ActivityNotFoundException anf) {
                Toast.makeText(
                        Launcher3DActivity.this,
                        Launcher3DActivity.this.getResources().getString(
                                R.string.package_not_find_ERROR_1), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void OnKey(MGameEvent event) {
            Log.i(TAG, "------>MyApp onKey:" + event.keyCode + "agent:" + event.agentName
                    + " entity:" + event.entityName);

        }

        @Override
        public void OnMouseOver(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnFocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnUnfocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnSwapEntity(MGameEvent event) {

        }
    }

    // define update handler
    class FavoriteUpdateHandler extends MUpdateHandler {
        public void update(String entityName) {
            // get Bitmap
            Bitmap bitmap = Launcher3DActivity.this.pageIcon.getFavoriteBitmap(entityName);
            if (bitmap != null) {
                agent.update(entityName, bitmap);
            } else {
                Log.v("MLauncher3DUpdateHandler", "!!!!!!!!can not getBitmap for " + entityName);
            }
        }

    }

    // define EventHandler
    class FavoriteEventHandler extends MEventHandler {

        @Override
        public void OnInit(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnClick(MGameEvent event) {
            // TODO Auto-generated method stub
            if ("Plane.003".endsWith(event.entityName)) {
                ComponentName componentName = new ComponentName("com.android.mslauncher",
                        "com.android.mslauncher.All3DAppActivity");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                Launcher3DActivity.this.startActivity(intent);

            } else {
                pageIcon.runApp(event.entityName);
            }

        }

        @Override
        public void OnSelect(MGameEvent event) {
            // TODO Auto-generated method stub
            Log.v(TAG, "+++++++++++++++++++MLauncher3DEventHandler was invoked evetn.agentName="
                    + event.agentName + ",event.entityName=" + event.entityName);
            if ("Plane.003".endsWith(event.entityName)) {
                ComponentName componentName = new ComponentName("com.android.mslauncher",
                        "com.android.mslauncher.All3DAppActivity");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                Launcher3DActivity.this.startActivity(intent);

            } else {
                changeInputSource(pageIcon.getRunApp(event.entityName));
                pageIcon.runApp(event.entityName);
            }
        }

        @Override
        public void OnKey(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnMouseOver(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnFocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnUnfocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnSwapEntity(MGameEvent event) {

        }
    }

    // ///////////////////////////////////////////////////////
    // define update handler
    class PreAppUpdateHandler extends MUpdateHandler {
        public void update(String entityName) {
            // get Bitmap
            Bitmap bitmap = Launcher3DActivity.this.pageIcon.getIconBitmap(entityName);
            if (bitmap != null) {
                agent.update(entityName, bitmap);
            } else {
                Log.v("MLauncher3DUpdateHandler", "!!!!!!!!can not getBitmap for " + entityName);
            }
        }

    }

    // define EventHandler
    class PreAppEventHandler extends MEventHandler {

        @Override
        public void OnInit(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnClick(MGameEvent event) {
            // TODO Auto-generated method stub
            pageIcon.runApp(event.entityName);
        }

        @Override
        public void OnSelect(MGameEvent event) {
            // TODO Auto-generated method stub
            Log.v(TAG, "+++++++++++++++++++MLauncher3DEventHandler was invoked evetn.agentName="
                    + event.agentName + ",event.entityName=" + event.entityName);
            changeInputSource(pageIcon.getRunApp(event.entityName));
            pageIcon.runApp(event.entityName);
        }

        @Override
        public void OnKey(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnMouseOver(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnFocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnUnfocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnSwapEntity(MGameEvent event) {

        }
    }

    // /////////////////////////////////////////////////////
    class MyAppUpdateHandler extends MUpdateHandler {
        public void update(String entityName) {
            // get Bitmap
            Bitmap bitmap = Launcher3DActivity.this.pageIcon.getIconBitmap(entityName);
            if (bitmap != null) {
                agent.update(entityName, bitmap);
            } else {
                agent.update(entityName, null);
                Log.v("MLauncher3DUpdateHandler", "!!!!!!!!can not getBitmap for " + entityName);
            }
        }

    }

    class MyAppEventHandler extends MEventHandler {

        @Override
        public void OnInit(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnClick(MGameEvent event) {
            // TODO Auto-generated method stub
            pageIcon.runApp(event.entityName);
        }

        @Override
        public void OnSelect(MGameEvent event) {
            // TODO Auto-generated method stub
            Log.v(TAG, "+++++++++++++++++++MLauncher3DEventHandler was invoked evetn.agentName="
                    + event.agentName + ",event.entityName=" + event.entityName);
            changeInputSource(pageIcon.getRunApp(event.entityName));
            pageIcon.runApp(event.entityName);
        }

        @Override
        public void OnKey(MGameEvent event) {
            Log.i(TAG, "------>MyApp onKey:" + event.keyCode + "agent:" + event.agentName
                    + " entity:" + event.entityName);

            if (event.keyCode == KeyEvent.KEYCODE_0 || event.keyCode == KeyEvent.KEYCODE_MENU) {
                Log.i(TAG, "------>keycode_0");

                deleteIcon(event.entityName, event.agentName);
                MstarOKitUI.getInstance().update(event.agentName, event.entityName);
            } else if (event.keyCode == KeyEvent.KEYCODE_1) {
                Log.i(TAG, "------>keycode_1");
                moveMode = !moveMode;
                if (moveMode == true) {
                    Toast toast = Toast.makeText(Launcher3DActivity.this,
                            getString(R.string.movemode), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(Launcher3DActivity.this,
                            getString(R.string.normalmode), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            } else if (event.keyCode == KeyEvent.KEYCODE_DPAD_LEFT && moveMode == true) {
                Log.i(TAG, "------>keycode_dpad_left");
                // when the key callback,the focus have changed to left
                String rightEntity = pageIcon.getRightEntity(event.entityName);
                if (rightEntity == null) {
                    moveMode = false;
                    Toast toast = Toast.makeText(Launcher3DActivity.this,
                            getString(R.string.normalmode), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                pageIcon.swapIcon(event.entityName, rightEntity);
                MstarOKitUI.getInstance().update(event.agentName, event.entityName);
                MstarOKitUI.getInstance().update(event.agentName, rightEntity);
            } else if (event.keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && moveMode == true) {
                Log.i(TAG, "------>keycode_dpad_right");
                // when the key callback,the focus have changed to right
                String leftEntity = pageIcon.getLeftEntity(event.entityName, event.agentName);
                if (leftEntity == null) {
                    moveMode = false;
                    Toast toast = Toast.makeText(Launcher3DActivity.this,
                            getString(R.string.normalmode), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                pageIcon.swapIcon(event.entityName, leftEntity);
                MstarOKitUI.getInstance().update(event.agentName, event.entityName);
                MstarOKitUI.getInstance().update(event.agentName, leftEntity);
            }
        }

        @Override
        public void OnMouseOver(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnFocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnUnfocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnSwapEntity(MGameEvent event) {
            Log.i(TAG, "-------------->Entity swap call back");
            pageIcon.moveEntity(event.entityName, event.peerEntityName);

            MstarOKitUI.getInstance().update(event.agentName, event.entityName);
            MstarOKitUI.getInstance().update(event.agentName, event.peerEntityName);

        }

    }

    // /////////////////////////////////////////////////////
    class WidgetUpdateHandler extends MUpdateHandler {
        public void update(String entityName) {
            // get Bitmap

            if (widgetBitmap != null) {
                agent.update(entityName, widgetBitmap);
            } else {
                Log.v("MLauncher3DUpdateHandler", "!!!!!!!!can not getBitmap for " + entityName);
            }
        }

    }

    class WidgetEventHandler extends MEventHandler {

        @Override
        public void OnInit(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnClick(MGameEvent event) {
            // TODO Auto-generated method stub
            ComponentName componentName = new ComponentName("com.jrm.babao.babaofan",
                    "com.jrm.babao.babaofan.activity.main.BabaofanMainActivity");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(componentName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            Launcher3DActivity.this.startActivity(intent);
        }

        @Override
        public void OnSelect(MGameEvent event) {
            // TODO Auto-generated method stub
            Log.v(TAG, "+++++++++++++++++++MLauncher3DEventHandler was invoked evetn.agentName="
                    + event.agentName + ",event.entityName=" + event.entityName);
            ComponentName componentName = new ComponentName("com.jrm.babao.babaofan",
                    "com.jrm.babao.babaofan.activity.main.BabaofanMainActivity");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(componentName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            changeInputSource("com.jrm.babao.babaofan");
            Launcher3DActivity.this.startActivity(intent);

        }

        @Override
        public void OnKey(MGameEvent event) {
            Log.i(TAG, "------>MyApp onKey:" + event.keyCode + "agent:" + event.agentName
                    + " entity:" + event.entityName);

        }

        @Override
        public void OnMouseOver(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnFocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnUnfocus(MGameEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void OnSwapEntity(MGameEvent event) {

        }
    }

    public void setWidgetBitmap(Bitmap bmp) {
        widgetBitmap = bmp;
        if (widgetBitmap != null) {
            MstarOKitUI.getInstance().update("RecApp", "Rec_App");
        }
    }
    // ///////////////////////////////////////////////////////////////
}
