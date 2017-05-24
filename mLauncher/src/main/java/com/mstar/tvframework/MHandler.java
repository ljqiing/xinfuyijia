
package com.mstar.tvframework;

import android.util.Log;

class MHandler {

    protected MGameEngine gameEngine;

    protected Agent agent;

    public enum handlerType {
        HANDLER_NOTYPE, HANDLER_UPDATE, HANDLER_EVENT
    };

    protected handlerType mtype = handlerType.HANDLER_NOTYPE;

    public handlerType getType() {
        return mtype;
    }

}

class MDefaultUpdateHandler extends MUpdateHandler {
    private static final String TAG = "MDefaultUpdateHandler";

    public MDefaultUpdateHandler() {
        Log.v(TAG, "MDefaultUpdateHandler.MDefaultUpdateHandler !!");
    }

    public void update(String entityName) {
        // do nothing
        Log.v(TAG, "MDefaultUpdateHandler.update !!");
    }
}

class MDefaultEventHandler extends MEventHandler {
    private static final String TAG = "MDefaultEventHandler";

    public MDefaultEventHandler() {
        Log.v(TAG, "MDefaultEventHandler.MDefaultEventHandler !!");
    }

    public void OnInit(MGameEvent event) {
        Log.v(TAG, "MDefaultEventHandler.onInit!!");
    }

    public void OnClick(MGameEvent event) {
        Log.v(TAG, "MDefaultEventHandler.OnClick !!");
    }

    public void OnSelect(MGameEvent event) {
        Log.v(TAG, "MDefaultEventHandler.OnSelect !!");
    }

    public void OnKey(MGameEvent event) {
        Log.v(TAG, "MDefaultEventHandler.OnKey !!");
    }

    public void OnMouseOver(MGameEvent event) {
        Log.v(TAG, "MDefaultEventHandler.OnMouseOver !!");
    }

    public void OnFocus(MGameEvent event) {
        Log.v(TAG, "MDefaultEventHandler.OnFocus !!");
    }

    public void OnUnfocus(MGameEvent event) {
        Log.v(TAG, "MDefaultEventHandler.OnUnfocus !!");
    }

    public void OnSwapEntity(MGameEvent event) {
        Log.v(TAG, "MDefaultEventHandler.OnSwapEntity");
    }
}
