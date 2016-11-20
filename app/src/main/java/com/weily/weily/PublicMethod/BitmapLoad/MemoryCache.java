package com.weily.weily.PublicMethod.BitmapLoad;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.weily.weily.Callback.ImageCache;

/**
 * Created by myste on 2016/11/20.
 * 内存缓存
 */

class MemoryCache implements ImageCache
{
    private LruCache<String, Bitmap> lruCache= null;
    MemoryCache()
    {
        int maxMemory = (int) Runtime.getRuntime().maxMemory()/1024;
        int cacheSizes = maxMemory/4;
        lruCache=new LruCache<>(cacheSizes);
    }

    @Override
    public void put(String url, Bitmap bitmap)
    {
        lruCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url)
    {
        return lruCache.get(url);
    }
}
