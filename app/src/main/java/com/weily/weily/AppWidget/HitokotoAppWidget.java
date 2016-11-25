package com.weily.weily.AppWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.weily.weily.PublicMethod.Logs;
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
        for (int appWidgetId : appWidgetIds)
        {
            idsSet.add(appWidgetId);
            updateWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds)
    {
        Logs.logi("onDeleted");
        for (int appWidgetId : appWidgetIds)
        {
            idsSet.remove(appWidgetId);
        }
        super.onDeleted(context, appWidgetIds);
    }

    private void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE);
        RemoteViews remoteViews;
        switch (sharedPreferences.getInt(context.getString(R.string.name_widget_text_alignment),1))
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
        //remoteViews.setTextColor();
        remoteViews.setTextViewText(R.id.widget_tv, widget);
        remoteViews.setOnClickPendingIntent(R.id.widget_tv, getPendingIntent(context));

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onEnabled(Context context)
    {
        Logs.logi("onEnabled");
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isEnabled", true);
        editor.apply();
        /**
         * 启用定时刷新
         */
        Intent intent = new Intent(context, WidgetService.class);
        context.startService(intent);
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context)
    {
        Logs.logi("onDisabled");
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isEnabled", false);
        editor.apply();
        Intent intent = new Intent(context, WidgetService.class);
        context.stopService(intent);
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Logs.logi("接收到广播:"+intent.getAction());
        super.onReceive(context, intent);
        String action = intent.getAction();
        if ("android.appwidget.action.APPWIDGET_UPDATE".equals(action))
        {
            String text=intent.getStringExtra("text");
            Logs.logi("test:"+text);
            updateAllAppWidgets(text, context, AppWidgetManager.getInstance(context), idsSet);
        } else if (intent.hasCategory(Intent.CATEGORY_ALTERNATIVE))
        {
            Intent intent1 = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
            context.sendBroadcast(intent1);
        }
    }

    private void updateAllAppWidgets(String word, Context context, AppWidgetManager appWidgetManager, Set<Integer> idsSet)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE);
        int appID;
        // 迭代器，用于遍历所有保存的widget的id
        for (Integer anIdsSet : idsSet)
        {
            appID = anIdsSet;
            RemoteViews remoteViews;
            switch (sharedPreferences.getInt(context.getString(R.string.name_widget_text_alignment),1))
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
            remoteViews.setTextViewText(R.id.widget_tv, word);
            //remoteViews.setTextColor();
            remoteViews.setOnClickPendingIntent(R.id.widget_tv, getPendingIntent(context));

            appWidgetManager.updateAppWidget(appID, remoteViews);
        }
    }

    private PendingIntent getPendingIntent(Context context)
    {
        Intent intent = new Intent();
        intent.setClass(context, HitokotoAppWidget.class);
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
