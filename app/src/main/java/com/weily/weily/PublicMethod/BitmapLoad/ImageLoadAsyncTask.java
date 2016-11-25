package com.weily.weily.PublicMethod.BitmapLoad;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by myste on 2016/11/20.
 * 异步加载
 */

public class ImageLoadAsyncTask extends AsyncTask<String, Void, Bitmap>
{
    private ImageView imageView;
    private String url;

    ImageLoadAsyncTask(ImageView imageView, String url)
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
            bitmap = ImageLoader.getImage(params[0]);
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

    private void addBitmapToLruCaches(String url, Bitmap bitmap)
    {
        new MemoryCache().put(url, bitmap);
        new DiskCache().put(GetInfo.getFileName(url), bitmap);
    }
}
