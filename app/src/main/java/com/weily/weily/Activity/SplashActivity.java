package com.weily.weily.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.weily.weily.Callback.ShowPageListener;
import com.weily.weily.PublicMethod.BitmapLoad.DiskCache;
import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.PublicMethod.ShowPage;
import com.weily.weily.R;

import java.util.Calendar;

public class SplashActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ExitApplication.getInstance().addActivity(this);
        ShowPage showPage=new ShowPage(SplashActivity.this);
        showPage.get(new ShowPageListener()
        {
            @Override
            public void done(Bitmap bitmap)
            {
                Calendar calendar=Calendar.getInstance();
                String date=calendar.get(Calendar.YEAR)+"_"+calendar.get(Calendar.MONTH)+"_"+calendar.get(Calendar.DATE);
                Intent intent=new Intent(SplashActivity.this,PageActivity.class);
                intent.putExtra(getString(R.string.intent_show_page),date);
                startActivity(intent);
                finish();
            }

            @Override
            public void cancel()
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
