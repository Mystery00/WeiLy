package com.weily.weily.PublicMethod;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.weily.weily.Callback.ShowPageListener;
import com.weily.weily.PublicMethod.BitmapLoad.DiskCache;
import com.weily.weily.R;

import java.util.Calendar;

/**
 * Created by myste on 2016/11/13.
 * 根据节日显示对应主题图片
 */

public class ShowPage
{
    private Context context;
    public ShowPage(Context context)
    {
        this.context=context;
    }
    public void get(final ShowPageListener showPageListener)
    {
        /**
         * 获取缓存的图片
         * 若未缓存则调用下载方法
         */
        final DiskCache diskCache = new DiskCache();
        Calendar calendar = Calendar.getInstance();
        final String date = calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.MONTH) + "_" + calendar.get(Calendar.DATE);
        Bitmap bitmap = diskCache.getBitmap(date);
        if (bitmap != null)
        {
            showPageListener.done(bitmap);
            return;
        }
        showPageListener.cancel();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new DiskCache());
        ImageLoader.ImageListener listener = new ImageLoader.ImageListener()
        {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b)
            {
                Logs.logi(String.valueOf(b));
                Logs.logi(imageContainer.getRequestUrl());
                diskCache.putBitmap(date, imageContainer.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                Logs.loge(volleyError.getMessage());
            }
        };
        imageLoader.get("http://git-sublime.github.io/test/weily/picture/logo.png", listener);

//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                /**
//                 * 调用下载方法，并缓存
//                 */
//                try
//                {
//                    Bitmap bitmap = ImageLoader.getImage(context.getString(R.string.url_show_page));
//                    diskCache.putBitmap(date, bitmap);
//                } catch (Exception e)
//                {
//                    Logs.loge(e);
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}
