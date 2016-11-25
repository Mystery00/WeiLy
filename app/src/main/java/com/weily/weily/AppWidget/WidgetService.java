package com.weily.weily.AppWidget;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.weily.weily.PublicMethod.Logs;
import com.weily.weily.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            getText();
            handler.postDelayed(runnable,getRefreshTime());
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

    private long getRefreshTime()
    {
        return getSharedPreferences(getString(R.string.file_sharedPreferences_widget),MODE_PRIVATE).getLong(getString(R.string.name_widget_refresh_time),300000);
    }

    private void getText()
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
                    Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
                    intent.putExtra("text",str.toString());
                    Logs.logi("发送广播");
                    sendBroadcast(intent);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
