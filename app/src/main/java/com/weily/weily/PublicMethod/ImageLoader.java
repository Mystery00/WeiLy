package com.weily.weily.PublicMethod;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

/**
 * listView异步加载图片
 * 网址：http://blog.csdn.net/dmk877/article/details/49366421
 */
public class ImageLoader
{
    private int maxMemory = (int) Runtime.getRuntime().maxMemory();
    private int cacheSizes = maxMemory/5;
    private LruCache<String, Bitmap> mMemoryCaches= new LruCache<>(cacheSizes);

    public void showImageByAsyncTask(ImageView imageView, String url)
    {
        //首先去LruCache中去找图片
        Bitmap bitmap = getBitmapFromLruCache(url);
        //如果不为空，说明LruCache中已经缓存了该图片，则读取缓存直接显示，
        if (bitmap != null)
        {
            imageView.setImageBitmap(bitmap);
        } else
        {
            //如果缓存中没有的话就开启异步任务去下载图片，
            new NewsAsyncTask(imageView, url).execute(url);
        }
    }

    private Bitmap getBitmapFromLruCache(String url)
    {
        return mMemoryCaches.get(url);
    }

    private void addBitmapToLruCaches(String url, Bitmap bitmap)
    {
        if (getBitmapFromLruCache(url) == null)
        {
            mMemoryCaches.put(url, bitmap);
        }
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap>
    {

        private ImageView imageView;
        private String url;

        NewsAsyncTask(ImageView imageView, String url)
        {
            this.imageView = imageView;
            this.url = url;
        }

        //String...params是可变参数接受execute中传过来的参数
        @Override
        protected Bitmap doInBackground(String... params)
        {
            Bitmap bitmap = null;
            try
            {
                //调用下载方法
                bitmap = DownloadHeadFile.getImage(params[0]);
                //下载完成之后将其加入到LruCache中这样下次加载的时候，就可以直接从LruCache中直接读取
                if (bitmap != null)
                {
                    addBitmapToLruCaches(url, bitmap);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return bitmap;
        }

        //这里的bitmap是从doInBackgroud中方法中返回过来的
        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);
            if (imageView.getTag().equals(url))
            {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}


