
package com.mstar.tvframework;

public abstract class MEventHandler extends MHandler {
    public MEventHandler() {
        mtype = handlerType.HANDLER_EVENT;
    }

    public abstract void OnInit(MGameEvent event);

    public abstract void OnClick(MGameEvent event);

    public abstract void OnSelect(MGameEvent event);

    public abstract void OnKey(MGameEvent event);

    public abstract void OnMouseOver(MGameEvent event);

    public abstract void OnFocus(MGameEvent event);

    public abstract void OnUnfocus(MGameEvent event);

    public abstract void OnSwapEntity(MGameEvent event);
}
