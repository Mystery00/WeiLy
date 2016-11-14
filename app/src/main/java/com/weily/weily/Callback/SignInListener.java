package com.weily.weily.Callback;

public interface SignInListener
{
    void Success();
    void Failure(int code,String message);
}
