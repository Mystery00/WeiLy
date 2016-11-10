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
 */

public class MyService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     *
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        get();
        return super.onStartCommand(intent, flags, startId);
    }

    private void get() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.lwl12.com/hitokoto/main/get");
                    while (true){
                        HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
                        httpurlconnection.connect();
                        InputStream inputstream = httpurlconnection.getInputStream();
                        InputStreamReader in = new InputStreamReader(inputstream);
                        BufferedReader br = new BufferedReader(in);
                        StringBuilder str = new StringBuilder();
                        String reader;
                        while ((reader = br.readLine()) != null){
                            str.append(reader);
                        }

                        Intent intent = new Intent();
                        intent.putExtra("text",str.toString());
                        intent.setAction("com.text");
                        sendBroadcast(intent);

                        Thread.sleep(5000);
                    }
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
