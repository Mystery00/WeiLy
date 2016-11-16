package com.weily.weily.AppWidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.weily.weily.R;

/**
 * Created by yangchao on 2016/11/10.
 */

public class setService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TAG",intent.getStringExtra("text"));
        set(intent.getStringExtra("text"));
        return super.onStartCommand(intent, flags, startId);
    }

    private void set(String text) {
        Log.i("TAG","更新视图");
        RemoteViews remoteview = new RemoteViews(getPackageName(), R.layout.new_app_widget);
        remoteview.setTextViewText(R.id.widget_tv,text);
        ComponentName componentname = new ComponentName(getApplicationContext(),NewAppWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
        manager.updateAppWidget(componentname,remoteview);
    }
}
