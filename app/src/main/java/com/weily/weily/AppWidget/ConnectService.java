package com.weily.weily.AppWidget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yangchao on 2016/11/9.
 *打开连接获取数据
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
                    while (true)
                    {
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

                        Intent intent = new Intent();
                        intent.putExtra("text", str.toString());
                        intent.setAction("com.text");
                        sendBroadcast(intent);

                        Thread.sleep(300000);//线程睡眠时间，即间隔时间
                    }
                } catch (java.io.IOException | InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
