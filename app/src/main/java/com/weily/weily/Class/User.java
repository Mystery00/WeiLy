package com.weily.weily.Class;

import com.weily.weily.Callback.SignInListener;

public class User
{
    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void login(SignInListener signInListener)
    {
        //执行登录操作
        signInListener.Success();
    }
}
