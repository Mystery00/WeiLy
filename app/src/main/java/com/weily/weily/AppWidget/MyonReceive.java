package com.weily.weily.AppWidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by yangchao on 2016/11/9.
 */

public class MyonReceive extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,setService.class);
        i.putExtra("text",intent.getStringExtra("text"));
        context.startService(i);
    }
}
