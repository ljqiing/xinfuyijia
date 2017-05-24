
package com.mstar.tvframework;

public abstract class MUpdateHandler extends MHandler {

    public static final String TAG = "MUpdateHandler";

    public MUpdateHandler() {
        mtype = handlerType.HANDLER_UPDATE;
    }

    public abstract void update(String entityName);

}
