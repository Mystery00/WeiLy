package com.weily.weily.AppWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.weily.weily.PublicMethod.Logs;
import com.weily.weily.PublicMethod.WidgetUtil;
import com.weily.weily.R;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yangchao on 2016/11/9.
 * 小部件显示控制
 */

public class HitokotoAppWidget extends AppWidgetProvider
{
    private static Set<Integer> idsSet = new HashSet<>();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        Logs.logi("onUpdate");
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds)
        {
            idsSet.add(appWidgetId);
            initWidget(context, appWidgetManager, appWidgetId);
        }
        WidgetUtil.updateAllAppWidgets(null,context,appWidgetManager,idsSet);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds)
    {
        Logs.logi("onDeleted");
        super.onDeleted(context, appWidgetIds);
        for (int appWidgetId : appWidgetIds)
        {
            idsSet.remove(appWidgetId);
        }
    }

    /**
     * 初始化widget
     */
    private void initWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE);
        RemoteViews remoteViews;
        switch (sharedPreferences.getInt(context.getString(R.string.name_widget_text_alignment), 1))
        {
            case 0://left
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout_start);
                break;
            case 2://right
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout_end);
                break;
            default:
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout_center);
                break;
        }
        CharSequence widget = context.getString(R.string.error_widget);
        remoteViews.setTextColor(R.id.widget_tv, WidgetUtil.getTextColor(context));
        remoteViews.setTextViewText(R.id.widget_tv, widget);
        remoteViews.setOnClickPendingIntent(R.id.widget_tv, WidgetUtil.getPendingIntent(context));

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onEnabled(Context context)
    {
        Logs.logi("onEnabled");
        super.onEnabled(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.name_widget_is_enabled), true);
        editor.apply();
        /**
         * 启用定时刷新
         */
        Intent intent = new Intent(context, WidgetService.class);
        context.startService(intent);
    }

    @Override
    public void onDisabled(Context context)
    {
        Logs.logi("onDisabled");
        super.onDisabled(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.name_widget_is_enabled), false);
        editor.apply();
        Intent intent = new Intent(context, WidgetService.class);
        context.stopService(intent);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Logs.logi("接收到广播:" + intent.getAction());
        super.onReceive(context, intent);
        String action = intent.getAction();
        if ("android.appwidget.action.APPWIDGET_UPDATE".equals(action))
        {
            String text = intent.getStringExtra(context.getString(R.string.intent_widget_text));
            Logs.logi("test:" + text);
            WidgetUtil.updateAllAppWidgets(text, context, AppWidgetManager.getInstance(context), idsSet);
        }
    }
}
