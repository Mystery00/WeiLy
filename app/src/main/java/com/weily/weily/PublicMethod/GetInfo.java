package com.weily.weily.PublicMethod;

/**
 * 获取信息的类
 */
public class GetInfo
{
    /**
     * 通过url获取文件名
     * @param url 地址
     * @return 文件名
     */
    public static String getFileName(String url)
    {
        int start = url.lastIndexOf("/");
        int end = url.lastIndexOf(".");
        if (start != -1 && end != -1)
        {
            return url.substring(start + 1, end);
        } else
        {
            return null;
        }

    }
}
