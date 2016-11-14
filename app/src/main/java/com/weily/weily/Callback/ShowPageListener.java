package com.weily.weily.Callback;

/**
 * Created by myste on 2016/11/13.
 * 根据节日显示对应主题图片监听
 */

public interface ShowPageListener
{
    void done(String path);
    void error(int code,String message);
}
