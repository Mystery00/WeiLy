package com.weily.weily.AppWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.weily.weily.R;

/**
 * Created by yangchao on 2016/11/9.
 *
 */

public class HitokotoAppWidget extends AppWidgetProvider{

    Intent intent;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId : appWidgetIds){
            updatewidget(context,appWidgetManager,appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private void updatewidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews remoteview = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        CharSequence widget = "Widget";
        remoteview.setTextViewText(R.id.widget_tv,widget);

        appWidgetManager.updateAppWidget(appWidgetId,remoteview);
    }

    @Override
    public void onEnabled(Context context) {
        intent = new Intent(context,ConnectService.class);
        context.startService(intent);
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        context.stopService(intent);
        super.onDisabled(context);
    }
}
