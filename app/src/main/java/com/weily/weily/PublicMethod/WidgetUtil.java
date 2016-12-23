package com.weily.weily.PublicMethod;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.RemoteViews;

import com.weily.weily.AppWidget.OnClickService;
import com.weily.weily.AppWidget.WidgetService;
import com.weily.weily.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

/**
 * Created by myste on 2016/11/25.
 * 小部件获取信息
 */

public class WidgetUtil
{
    public static void getText(final Context context)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    URL url = new URL(context.getString(R.string.url_widget));
                    HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
                    httpurlconnection.connect();
                    InputStream inputstream = httpurlconnection.getInputStream();
                    InputStreamReader in = new InputStreamReader(inputstream);
                    BufferedReader br = new BufferedReader(in);
                    StringBuilder str = new StringBuilder();
                    String reader;
                    while ((reader = br.readLine()) != null)
                    {
                        str.append(reader);
                    }
                    Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
                    intent.putExtra(context.getString(R.string.intent_widget_text), str.toString());
                    context.sendBroadcast(intent);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static PendingIntent getPendingIntent(Context context)
    {
        return PendingIntent.getService(context, 0, new Intent(context, OnClickService.class), 0);
    }

    public static long getRefreshTime(Context context)
    {
        return context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE).getLong(context.getString(R.string.name_widget_refresh_time), 300000);
    }

    public static int getTextColor(Context context)
    {
        String color=context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget),Context.MODE_PRIVATE).getString(context.getString(R.string.name_widget_text_color),"#FFFFFF");
        return Color.parseColor(color);
    }

    public static void updateAllAppWidgets(String word, Context context, AppWidgetManager appWidgetManager, Set<Integer> idsSet)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE);
        if (word==null)
        {
            word=sharedPreferences.getString(context.getString(R.string.name_widget_text_now),context.getString(R.string.error_widget));
        }
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.name_widget_text_now),word);
        editor.apply();
        int appID;
        // 迭代器，用于遍历所有保存的widget的id
        for (Integer anIdsSet : idsSet)
        {
            appID = anIdsSet;
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
            remoteViews.setTextViewText(R.id.widget_tv, word);
            remoteViews.setTextColor(R.id.widget_tv, getTextColor(context));
            remoteViews.setOnClickPendingIntent(R.id.widget_tv, WidgetUtil.getPendingIntent(context));

            appWidgetManager.updateAppWidget(appID, remoteViews);
        }
    }

    public static void updateWidget(Context context)
    {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra(context.getString(R.string.intent_widget_text), context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE).getString(context.getString(R.string.name_widget_text_now),context.getString(R.string.error_widget)));
        context.sendBroadcast(intent);
    }

    public static void restartService(Context context)
    {
        Intent intent=new Intent(context, WidgetService.class);
        context.stopService(intent);
        context.startService(intent);
    }
}
