package com.weily.weily.PublicMethod.BitmapLoad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.weily.weily.PublicMethod.FileDo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader
{
    private DiskCache diskCache = new DiskCache();
    private MemoryCache memoryCache = new MemoryCache();

    public void DisplayImage(ImageView imageView, String url)
    {
        //首先去LruCache中去找图片
        Bitmap bitmap = getBitmapFromLruCache(url);
        if (bitmap == null)//判断是否为空，若为空则去sd缓存找
        {
            bitmap = getBitmapFromDiskCache(url);
        }
        //如果不为空，说明LruCache中已经缓存了该图片，则读取缓存直接显示，
        if (bitmap != null)
        {
            imageView.setImageBitmap(bitmap);
        } else
        {
            //如果缓存中没有的话就开启异步任务去下载图片，
            new ImageLoadAsyncTask(imageView,url).execute(url);
        }
    }

    private Bitmap getBitmapFromLruCache(String url)
    {
        return memoryCache.get(url);
    }

    private Bitmap getBitmapFromDiskCache(String url)
    {
        return diskCache.get(FileDo.getFileName(url));
    }

    public static Bitmap getImage(final String address)
    {
        Bitmap bitmap = null;
        try
        {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }
}


