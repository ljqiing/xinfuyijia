
package com.mstar.tvframework;

import android.util.Log;

public class MGameEvent {

    private static String TAG = "MGameEvent";

    public enum EventType {
        E_EVENT_NIL, E_EVENT_INIT, E_EVENT_CLICK, E_EVENT_SELECT, E_EVENT_UPDATE, E_EVENT_KEY, E_EVENT_MOUSEOVER, E_EVENT_FOCUS, E_EVENT_UNFOCUS, E_EVENT_SWAPPOS
    };

    public String sceneName;

    public String agentName;

    public String entityName;

    public String animName;

    public int keyCode;

    public float mousePosx;

    public float mousePosy;

    public EventType mType;

    public String peerEntityName;

    public String getAgentName() {
        return agentName;
    }

    public MGameEvent(String agnetName, EventType type) {
        setmType(type);
        setAgentName(agnetName);
    }

    /**
     * @param agentName the agentName to set
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * @return the mType
     */
    public EventType getmType() {
        return mType;
    }

    /**
     * @param mType the mType to set
     */
    public void setmType(EventType mType) {
        this.mType = mType;
    }

    /**
     * @return the sceneName
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * @param sceneName the sceneName to set
     */
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    /**
     * @return the animName
     */
    private String getAnimName() {
        return animName;
    }

    /**
     * @param animName the animName to set
     */
    private void setAnimName(String animName) {
        this.animName = animName;
    }

    /**
     * @return the keyCode
     */
    private int getKeyCode() {
        return keyCode;
    }

    /**
     * @param keyCode the keyCode to set
     */
    private void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * @return the mousePosx
     */
    private float getMousePosx() {
        return mousePosx;
    }

    /**
     * @param mousePosx the mousePosx to set
     */
    private void setMousePosx(float mousePosx) {
        this.mousePosx = mousePosx;
    }

    /**
     * @return the mousePosy
     */
    private float getMousePosy() {
        return mousePosy;
    }

    /**
     * @param mousePosy the mousePosy to set
     */
    private void setMousePosy(float mousePosy) {
        this.mousePosy = mousePosy;
    }

    public void transformEventType(int type) {
        Log.v(TAG, "+++++++++++++++MGameEvent transformEventType type=" + type);
        switch (type) {
            case 0: {
                mType = EventType.E_EVENT_NIL;
                break;
            }
            case 1: {
                mType = EventType.E_EVENT_INIT;
                break;
            }
            case 2: {
                mType = EventType.E_EVENT_CLICK;
                break;
            }
            case 3: {
                mType = EventType.E_EVENT_SELECT;
                break;
            }
            case 4: {
                mType = EventType.E_EVENT_UPDATE;
                break;
            }
            case 5: {
                mType = EventType.E_EVENT_KEY;
                break;
            }
            case 6: {
                mType = EventType.E_EVENT_MOUSEOVER;
                break;
            }
            case 7: {
                mType = EventType.E_EVENT_FOCUS;
                break;
            }
            case 8: {
                mType = EventType.E_EVENT_UNFOCUS;
                break;
            }
            case 9: {
                mType = EventType.E_EVENT_SWAPPOS;
                break;
            }
            default:
                mType = EventType.E_EVENT_NIL;
                break;
        }
    }
}
