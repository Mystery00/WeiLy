package com.weily.weily.AppWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.weily.weily.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
        for (int appWidgetId : appWidgetIds)
        {
            idsSet.add(appWidgetId);
            updatewidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds)
    {
        for (int appWidgetId : appWidgetIds)
        {
            idsSet.remove(appWidgetId);
        }
        super.onDeleted(context, appWidgetIds);
    }

    private void updatewidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId)
    {
        RemoteViews remoteview = new RemoteViews(context.getPackageName(), R.layout.widget_layout_start);
        CharSequence widget = context.getString(R.string.error_widget);
        remoteview.setTextViewText(R.id.widget_tv, widget);

        appWidgetManager.updateAppWidget(appWidgetId, remoteview);
    }

    @Override
    public void onEnabled(Context context)
    {
        Log.i("TAG", "onEnabled");
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_sharedPreferences_widget), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isEnabled", true);
        editor.apply();
        Intent intent = new Intent(context, WidgetService.class);
        context.startService(intent);
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context)
    {
        Log.i("TAG", "onDisabled");
        Intent intent = new Intent(context, WidgetService.class);
        context.stopService(intent);
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();

        if ("android.appwidget.action.APPWIDGET_UPDATE".equals(action))
        {
            MyThread(context);
        } else if (intent.hasCategory(Intent.CATEGORY_ALTERNATIVE))
        {
            Intent intent1 = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
            context.sendBroadcast(intent1);
        }
        super.onReceive(context, intent);
    }

    private void updateAllAppWidgets(String word, Context context, AppWidgetManager appWidgetManager, Set<Integer> idsSet)
    {
        int appID;
        // 迭代器，用于遍历所有保存的widget的id
        for (Integer anIdsSet : idsSet)
        {
            appID = anIdsSet;
            RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_layout_start);
            remoteView.setTextViewText(R.id.widget_tv, word);
            remoteView.setOnClickPendingIntent(R.id.widget_tv, getPendingIntent(context));

            appWidgetManager.updateAppWidget(appID, remoteView);
        }
    }

    private void MyThread(final Context context)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    URL url = new URL("https://api.lwl12.com/hitokoto/main/get");
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
                    updateAllAppWidgets(str.toString(), context, AppWidgetManager.getInstance(context), idsSet);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private PendingIntent getPendingIntent(Context context)
    {
        Intent intent = new Intent();
        intent.setClass(context, HitokotoAppWidget.class);
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
