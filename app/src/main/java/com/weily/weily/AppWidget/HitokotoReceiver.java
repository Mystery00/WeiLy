package com.weily.weily.AppWidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by yangchao on 2016/11/9.
 *消息接收器
 */

public class HitokotoReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,ChangeTextService.class);
        i.putExtra("text",intent.getStringExtra("text"));
        context.startService(i);
    }
}
