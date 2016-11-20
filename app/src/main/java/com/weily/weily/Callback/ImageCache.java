package com.weily.weily.Callback;

import android.graphics.Bitmap;

/**
 * Created by myste on 2016/11/20.
 * 图片缓存接口
 */

public interface ImageCache
{
    void put(String url, Bitmap bitmap);
    Bitmap get(String url);
}
