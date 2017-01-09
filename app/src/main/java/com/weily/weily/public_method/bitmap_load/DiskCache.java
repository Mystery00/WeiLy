package com.weily.weily.public_method.bitmap_load;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.android.volley.toolbox.ImageLoader;
import com.weily.weily.public_method.FileDo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by myste on 2016/11/20.
 * sd卡缓存
 */

public class DiskCache implements ImageLoader.ImageCache
{
    private static String CacheDir= Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.weily.weily/cache/";

    @Override
    public Bitmap getBitmap(String url)
    {
        return BitmapFactory.decodeFile(CacheDir + url + ".png");
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap)
    {
        if(FileDo.isFolderExists(CacheDir))
        {
            FileOutputStream fileOutputStream = null;
            try
            {
                File file = new File(CacheDir + url + ".png");
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } catch (IOException ignored)
            {
            } finally
            {
                if (fileOutputStream != null)
                {
                    try
                    {
                        fileOutputStream.close();
                    } catch (IOException ignored)
                    {
                    }
                }
            }
        }
    }
}
