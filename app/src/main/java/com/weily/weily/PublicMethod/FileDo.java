package com.weily.weily.PublicMethod;

import java.io.File;

/**
 * Created by myste on 2016/11/21.
 * 文件夹操作类
 */

public class FileDo
{
    /**
     * 检测文件夹是否存在，不存在则创建
     * @param strFolder 文件夹路径
     * @return 是否创建
     */
    public static boolean isFolderExists(String strFolder)
    {
        File file = new File(strFolder);
        return file.exists() || file.mkdirs();
    }

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
