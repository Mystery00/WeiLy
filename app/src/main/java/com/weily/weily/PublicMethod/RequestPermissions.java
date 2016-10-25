package com.weily.weily.PublicMethod;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class RequestPermissions
{
    public static void request(Context context,String permissions)
    {
        if (ContextCompat.checkSelfPermission(context, permissions)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            //ActivityCompat.requestPermissions(context, new String[]{permissions},
            //        WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }
}
