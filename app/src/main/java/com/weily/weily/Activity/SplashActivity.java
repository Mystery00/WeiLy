package com.weily.weily.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.weily.weily.Callback.ShowPageListener;
import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.PublicMethod.ShowPage;

public class SplashActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ExitApplication.getInstance().addActivity(this);
        ShowPage.get(new ShowPageListener()
        {
            @Override
            public void done(String path)
            {
                startActivity(new Intent(SplashActivity.this,PageActivity.class));
                finish();
            }

            @Override
            public void error(int code, String message)
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
