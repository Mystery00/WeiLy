package com.weily.weily.AppWidget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.weily.weily.PublicMethod.Logs;
import com.weily.weily.R;

/**
 * Created by yangchao on 2016/11/9.
 * while发送广播
 */

public class WidgetService extends Service
{
    private UpDateThread mUpdateThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        mUpdateThread = new UpDateThread();
        mUpdateThread.start();
        super.onCreate();
    }

    @Override
    public void onDestroy()
    {
        mUpdateThread.interrupt();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }


    class UpDateThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                //noinspection InfiniteLoopStatement
                while (true)
                {
                    Logs.logi("开始更新！");
                    Intent intent = new Intent();
                    intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
                    sendBroadcast(intent);

                    Thread.sleep(getSharedPreferences(getString(R.string.file_sharedPreferences_widget),MODE_PRIVATE).getLong(getString(R.string.name_widget_refresh_time),300000));//线程睡眠时间，即间隔时间
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
