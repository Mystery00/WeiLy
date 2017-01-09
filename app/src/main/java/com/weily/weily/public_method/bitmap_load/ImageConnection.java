package com.weily.weily.public_method.bitmap_load;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mystery0 on 16-12-11.
 * 获取image
 */

public class ImageConnection
{
    public static Bitmap getImage(final String address)
    {
        Bitmap bitmap = null;
        try
        {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (IOException ignored)
        {
        }
        return bitmap;
    }
}