/*
 * Copyright (C) 2012 The Mstar SH team
 *
 */

package com.mstar.tvframework;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.util.Log;

public class Agent {

    public static final String AGENT_TAG = "AGENT";

    public static final String SCENE_TAG = "SCENE";

    public static final String GOBJ_TAG = "GOBJ";

    public static final String SUBENT_TAG = "SUBENT";

    public static final String TECH_TAG = "TECH";

    public static final String PASS_TAG = "PASS";

    public static final String TEXUNIT_TAG = "TEXUNIT";

    public static final String PF_TAG = "PF";

    protected String name;

    protected MGameEngine gameEngine;

    // TODO: add other agent information define
    protected MUpdateHandler updateHandler;

    protected MEventHandler eventHandler;

    private ArrayList<Entity> children = new ArrayList<Entity>();

    public Agent() {
    }

    public void update(String entityName, Bitmap image) {
        // Log.v(AGENT_TAG, "+++Agent:Update invoked entityName=" + entityName);
        Entity destEntity = getEntityByName(entityName);
        if (destEntity != null) {
            // Log.v(AGENT_TAG, "!!!!!!!Agent:Update find entity to update");
            destEntity.update(image);
        }
    }

    public void appendChild(Entity entity) {
        children.add(entity);
    }

    public boolean bind(MHandler h) {
        h.agent = this;
        h.gameEngine = MGameEngine.getInstance();
        if (h.getType() == MHandler.handlerType.HANDLER_EVENT) {
            eventHandler = (MEventHandler) h;
            return true;
        }
        if (h.getType() == MHandler.handlerType.HANDLER_UPDATE) {
            updateHandler = (MUpdateHandler) h;
            return true;
        }

        return false;
    }

    public boolean dispatchEvent(MGameEvent e) {
        // update event
        Log.v(AGENT_TAG, "+++++++Agent dispatch agent name=" + name);
        if (e.mType == MGameEvent.EventType.E_EVENT_UPDATE) {
            if (updateHandler != null) {
                Log.v(AGENT_TAG, "+++++++++++Agent dispatch updateHander is not null!!");
                updateHandler.update(e.entityName);
            }
        } else if (e.getmType() == MGameEvent.EventType.E_EVENT_INIT) {
            if (eventHandler != null) {
                eventHandler.OnInit(e);
            }
        } else if (e.getmType() == MGameEvent.EventType.E_EVENT_CLICK) {
            if (eventHandler != null) {
                eventHandler.OnClick(e);
            }
        } else if (e.getmType() == MGameEvent.EventType.E_EVENT_FOCUS) {
            // Log.v(AGENT_TAG,"+++MGameEvent.EventType.E_EVENT_FOCUS was invoked!!");
            // Log.v(AGENT_TAG, "e.getAgentName()=" + e.getAgentName());
            // Log.v(AGENT_TAG, "e.getSceneName()=" + e.getSceneName());
            if (eventHandler != null) {
                Log.v(AGENT_TAG, "!!!!!!!!!!!!! eventHandler!=null ok!!");
                eventHandler.OnFocus(e);
            }
        } else if (e.getmType() == MGameEvent.EventType.E_EVENT_UNFOCUS) {
            if (eventHandler != null) {
                eventHandler.OnUnfocus(e);
            }
        } else if (e.getmType() == MGameEvent.EventType.E_EVENT_SELECT) {
            if (eventHandler != null) {
                eventHandler.OnSelect(e);
            }
        } else if (e.getmType() == MGameEvent.EventType.E_EVENT_KEY) {
            if (eventHandler != null) {
                eventHandler.OnKey(e);
            }
        } else if (e.getmType() == MGameEvent.EventType.E_EVENT_MOUSEOVER) {
            if (eventHandler != null) {
                eventHandler.OnMouseOver(e);
            }
        } else if (e.getmType() == MGameEvent.EventType.E_EVENT_SWAPPOS) {
            if (eventHandler != null) {
                eventHandler.OnSwapEntity(e);
            }
        }
        return true;
    }

    public Entity getEntityByName(String entityName) {
        Entity destEntity = null;
        int nSize = children.size();
        for (int i = 0; i < nSize; i++) {
            if (children.get(i).getName().equals(entityName)) {
                destEntity = children.get(i);
                break;
            }
        }
        return destEntity;
    }

    public ArrayList<Entity> getEntitys() {
        return children;
    }

    public String getAgentName() {
        return name;
    }
}
