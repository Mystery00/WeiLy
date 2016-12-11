package com.weily.weily.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.weily.weily.Callback.ShowPageListener;
import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.PublicMethod.ShowPage;
import com.weily.weily.R;

import java.util.Calendar;

public class SplashActivity extends AppCompatActivity
{
    final private static int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ExitApplication.getInstance().addActivity(this);

        /**
         * 获取权限
         */
        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else
        {
            doNext();
        }
    }

    /**
     * 权限申请回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                doNext();
            } else
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }
    }

    /**
     * 执行下一步操作
     */
    private void doNext()
    {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        ShowPage showPage = new ShowPage(SplashActivity.this);
        showPage.get(new ShowPageListener()
        {
            @Override
            public void done(Bitmap bitmap)
            {
                Calendar calendar = Calendar.getInstance();
                String date = calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.MONTH) + "_" + calendar.get(Calendar.DATE);
                Intent intent = new Intent(SplashActivity.this, PageActivity.class);
                intent.putExtra(getString(R.string.intent_show_page), date);
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
