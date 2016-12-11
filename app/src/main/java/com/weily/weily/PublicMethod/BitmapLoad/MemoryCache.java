package com.weily.weily.PublicMethod.BitmapLoad;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by myste on 2016/11/20.
 * 内存缓存
 */

class MemoryCache implements ImageLoader.ImageCache
{
    private LruCache<String, Bitmap> lruCache= null;
    MemoryCache()
    {
        int maxMemory = (int) Runtime.getRuntime().maxMemory()/1024;
        int cacheSizes = maxMemory/4;
        lruCache=new LruCache<>(cacheSizes);
    }

    @Override
    public Bitmap getBitmap(String url)
    {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap)
    {
        lruCache.put(url, bitmap);
    }
}
