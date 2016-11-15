package com.weily.weily.AppWidget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.weily.weily.PublicMethod.Logs;
import com.weily.weily.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yangchao on 2016/11/9.
 * 打开连接获取数据
 */

public class ConnectService extends Service
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    /**
     *
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        get();
        return super.onStartCommand(intent, flags, startId);
    }

    private void get()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    URL url = new URL("https://api.lwl12.com/hitokoto/main/get");
                    //noinspection InfiniteLoopStatement
                    while (getSharedPreferences(getString(R.string.file_sharedPreferences_widget),MODE_PRIVATE).getBoolean(getString(R.string.name_widget_auto_refresh),true))
                    {
                        HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
                        httpurlconnection.setConnectTimeout(20000);
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

                        Intent intent = new Intent();
                        intent.putExtra("text", str.toString());
                        intent.setAction("com.Hitokoto.text");
                        sendBroadcast(intent);

                        Thread.sleep(getSharedPreferences(getString(R.string.file_sharedPreferences_widget),MODE_PRIVATE).getLong(getString(R.string.name_widget_refresh_time),300000));
                    }
                } catch (java.io.IOException | InterruptedException e)
                {
                    Logs.loge(e);
                }
            }
        }).start();
    }
}
