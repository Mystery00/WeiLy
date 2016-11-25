package com.weily.weily.AppWidget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.weily.weily.PublicMethod.WidgetUtil;

/**
 * 创建service用于获取信息，获取完成后销毁
 */
public class OnClickService extends Service
{

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        WidgetUtil.getText(getApplicationContext());
        stopService(new Intent(getApplicationContext(),OnClickService.class));
    }
}
