
package com.android.mslauncher;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;

public class AppWidget3DHost extends AppWidgetHost {

    public AppWidget3DHost(Context context, int hostId) {
        super(context, hostId);
    }

    @Override
    protected AppWidgetHostView onCreateView(Context context, int appWidgetId,
            AppWidgetProviderInfo appWidget) {
        return new AppWidget3DHostView(context);
    }

    @Override
    public void stopListening() {
        super.stopListening();
        clearViews();
    }
}
