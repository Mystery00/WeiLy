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
}
