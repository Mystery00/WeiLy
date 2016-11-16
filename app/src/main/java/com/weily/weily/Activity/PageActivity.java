package com.weily.weily.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.PublicMethod.Logs;
import com.weily.weily.R;

public class PageActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        ExitApplication.getInstance().addActivity(this);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);
                    startActivity(new Intent(PageActivity.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e)
                {
                    Logs.loge(e);
                    startActivity(new Intent(PageActivity.this,MainActivity.class));
                    finish();
                }
            }
        }).start();
    }
}
