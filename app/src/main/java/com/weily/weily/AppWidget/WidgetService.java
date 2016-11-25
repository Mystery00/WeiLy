package com.weily.weily.AppWidget;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.weily.weily.PublicMethod.WidgetUtil;

/**
 * Created by yangchao on 2016/11/9.
 * while发送广播
 */

public class WidgetService extends Service
{
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable()
    {
        @Override
        public void run()
        {
            WidgetUtil.getText(getApplicationContext());
            handler.postDelayed(runnable,WidgetUtil.getRefreshTime(getApplicationContext()));
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        handler.post(runnable);
        super.onCreate();
    }
}
