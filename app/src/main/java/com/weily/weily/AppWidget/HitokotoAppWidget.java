package com.weily.weily.AppWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.weily.weily.R;

/**
 * Created by yangchao on 2016/11/9.
 * 小部件显示控制
 */

public class HitokotoAppWidget extends AppWidgetProvider
{
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        for (int appWidgetId : appWidgetIds)
        {
            updatewidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private void updatewidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId)
    {
        RemoteViews remoteview = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        CharSequence widget = context.getString(R.string.error_widget);
        remoteview.setTextViewText(R.id.widget_tv, widget);

        appWidgetManager.updateAppWidget(appWidgetId, remoteview);
    }

    @Override
    public void onEnabled(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("isEnabled",true);
        editor.apply();
        Intent intent = new Intent(context, ConnectService.class);
        context.startService(intent);
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context)
    {
        Intent intent = new Intent(context, ConnectService.class);
        context.stopService(intent);
        intent = new Intent(context, ChangeTextService.class);
        context.stopService(intent);
        super.onDisabled(context);
    }
}
