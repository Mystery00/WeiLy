package com.weily.weily.PublicMethod;

import android.util.Log;

public class Logs
{
    public static void logi(String info)
    {
        Log.i("info",info);
    }
    public static void loge(Exception e)
    {
        Log.e("error",e.getMessage());
    }

    public static void loge(int code,String message)
    {
        Log.e("error",String.valueOf(code));
        Log.e("error",message);
    }
    public static void loge(String message)
    {
        Log.e("error",message);
    }
}
