package com.weily.weily.PublicMethod.BitmapLoad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.weily.weily.Callback.ImageCache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by myste on 2016/11/20.
 * sd卡缓存
 */

public class DiskCache implements ImageCache
{
    private static String CacheDir= Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.weily.weily/cache/";

    @Override
    public void put(String url, Bitmap bitmap)
    {
        FileOutputStream fileOutputStream=null;
        try
        {
            File file=new File(CacheDir+url+".png");
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
            fileOutputStream=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if(fileOutputStream!=null)
            {
                try
                {
                    fileOutputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public Bitmap get(String url)
    {
        return BitmapFactory.decodeFile(CacheDir+url+".png");
    }
}
