package com.weily.weily;

import android.app.Application;

import com.weily.weily.PublicMethod.CrashHandler;

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}
