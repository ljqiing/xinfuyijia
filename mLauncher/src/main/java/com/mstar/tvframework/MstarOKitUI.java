
package com.mstar.tvframework;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

public class MstarOKitUI {

    // debug flags
    private final static boolean LOG_ALL_FUNCTION_CALL = true;

    private final static String TAG = "MstarOKitUI";

    private final static String HANDLE_EVENT_THREAD = "HandleEventThread";

    private final static String VETEXTURE_NAME = "dynamicTexture";

    private final static String VETEXTURE_GROUP = "General";

    private final static String VETEXTURE_PIXELFORMAT = "PF_A8R8G8B8";

    private final static int WIDTH_VE = 320;

    private final static int HEIGHT_VE = 240;

    private AgentManager agentManager;

    private boolean bInitialized = false;

    private static MstarOKitUI instance = null;

    private HandlerThread handleEventThread = null;

    private Handler eventHandler = null;

    // EventRunnable dispatch event to AgentManager
    class EventRunnable implements Runnable {

        private MGameEvent gameEvent = null;

        public void setGameEvent(MGameEvent event) {
            gameEvent = event;
        }

        public void run() {
            if (MstarOKitUI.this.agentManager != null && gameEvent != null) {
                MstarOKitUI.this.agentManager.handleEvent(gameEvent);
            }
        }
    }

    // used to dispatch runLuaScript event
    class LuaScriptRunnable implements Runnable {

        private String luaScript = null;

        public void setLuaScript(String script) {
            luaScript = script;
        }

        public void run() {
            if ((luaScript != null) && !(luaScript.trim().isEmpty())) {
                MGameEngine.getInstance().runLuaScript(luaScript);
            }
        }
    }

    private MstarOKitUI() {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "MstarOKitUI.MstarOKitUI");
        }
    }

    public static MstarOKitUI getInstance() {
        if (instance == null)
            instance = new MstarOKitUI();
        return instance;
    }

    public boolean init(MGameEngine g) {

        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "MstarOKitUI.init");
        }

        if (!bInitialized) {
            agentManager = new AgentManager();
            agentManager.init(g);
            bInitialized = true;

            // start handleEventThread
            handleEventThread = new HandlerThread(HANDLE_EVENT_THREAD);
            handleEventThread.start();
            eventHandler = new Handler(handleEventThread.getLooper()) {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                }
            };
        }

        return true;
    }

    public boolean bind(MHandler h, String agentName) {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "MstarOKitUI.bind");
        }

        if (bInitialized) {
            return agentManager.bind(h, agentName);
        }
        return false;
    }

    public boolean unbind(MEventHandler h, String agentName) {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "MstarOKitUI.unbind");
        }

        if (bInitialized) {
            return agentManager.unbind(h, agentName);
        }
        return false;
    }

    public boolean update(String agentName, String entityName) {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "MstarOKitUI.update agentName=" + agentName + ",entityName=" + entityName);
        }

        if (bInitialized) {
            // generate update E_EVENT_UPDATE event
            Agent agent = agentManager.getAgent(agentName);
            if (agent == null) {
                Log.v(TAG, "MstarOKitUI.update can not find agent " + agentName);
                return false;
            }
            MGameEvent event = new MGameEvent(agentName, MGameEvent.EventType.E_EVENT_UPDATE);
            event.agentName = translateAgentName(agentName);
            event.entityName = entityName;
            EventRunnable r = new EventRunnable();
            r.setGameEvent(event);
            eventHandler.post(r);
            return true;

            // return agentManager.handleEvent(event);

        }
        return false;
    }

    public void finalize() {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "MstarOKitUI.finalize");
        }
    }

    public boolean dispatchEvent(MGameEvent event) {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "MstarOKitUI.dispatchEvent");
        }
        EventRunnable r = new EventRunnable();
        event.agentName = translateAgentName(event.agentName);
        r.setGameEvent(event);
        eventHandler.post(r);
        return true;
        // return agentManager.handleEvent(event);
    }

    private String translateAgentName(String agentName) {
        return agentManager.translateAgentName(agentName);
    }

    public void prepareVECapture() {
        MGameEngine.getInstance().createDynamicTexture(WIDTH_VE, HEIGHT_VE, 0,
                VETEXTURE_PIXELFORMAT, VETEXTURE_NAME, VETEXTURE_GROUP, 0, 0, false, 0, 0);

    }

    public void bindVECapture(String agentName, String entityName) {
        Agent agent = agentManager.getAgent(agentName);
        if (agent == null) {
            Log.v(TAG, "!!!!!!!!!!!!Can not find agent " + agentName);
            return;
        }
        Entity entity = agent.getEntityByName(entityName);
        if (entity == null) {
            Log.v(TAG, "!!!!!!!!!!Can not find Entity " + entityName);
            return;
        }
        MGameEngine.getInstance().replacewithDynamicTex("testApp", entity.getSceneName(),
                entityName, 0, 0, 0, 0);
    }

    public void startVECapture() {
        MGameEngine.getInstance().startVECapture();
        MGameEngine.getInstance().enableUpdateDynamicTex(true);
    }

    public void endVECapture() {
        MGameEngine.getInstance().enableUpdateDynamicTex(false);
        MGameEngine.getInstance().endVECapture();
    }

    public void setVECaptureAttr(String attribute, int value) {
        MGameEngine.getInstance().setVECaptureAttr(attribute, value);
    }

    public int queryVECaptureAttr(String attribute) {
        return MGameEngine.getInstance().queryVECaptureAttr(attribute);

    }

    public AgentManager getAgentManager() {
        return agentManager;
    }

    public void runLuaScript(String script) {
        LuaScriptRunnable r = new LuaScriptRunnable();
        r.setLuaScript(script);
        eventHandler.post(r);
    }
}
