package com.weily.weily.PublicMethod;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadHeadFile
{
    private static byte[] getBytes(InputStream is) throws Exception
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1)
        {
            bos.write(buffer, 0, len);
        }
        is.close();
        bos.flush();
        return bos.toByteArray();
    }

    public static Bitmap getImage(final String address) throws Exception
    {
        //通过代码 模拟器浏览器访问图片的流程
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        //获取服务器返回回来的流
        InputStream is = conn.getInputStream();
        byte[] imageBytes = getBytes(is);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}
