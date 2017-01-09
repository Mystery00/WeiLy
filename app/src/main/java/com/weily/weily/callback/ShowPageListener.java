package com.weily.weily.callback;

import android.graphics.Bitmap;

/**
 * Created by myste on 2016/11/13.
 * 根据节日显示对应主题图片监听
 */

public interface ShowPageListener
{
    void done(Bitmap bitmap);
    void cancel();
}
