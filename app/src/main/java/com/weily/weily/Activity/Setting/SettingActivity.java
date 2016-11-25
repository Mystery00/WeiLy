package com.weily.weily.Activity.Setting;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.R;

public class SettingActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private RelativeLayout layout_widget;
    private RelativeLayout layout_about;
    private Switch auto_login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(R.layout.activity_setting);

        initialization();

        monitor();
    }

    private void initialization()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ExitApplication.getInstance().addActivity(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        layout_widget = (RelativeLayout) findViewById(R.id.content_settings_relativeLayoutTwo);
        layout_about = (RelativeLayout) findViewById(R.id.content_settings_relativeLayoutThree);
        auto_login=(Switch)findViewById(R.id.switch_auto);
        auto_login.setChecked(getSharedPreferences(getString(R.string.file_sharedPreferences_widget),MODE_PRIVATE).getBoolean(getString(R.string.name_auto_login),false));
        setSupportActionBar(toolbar);

    }

    private void monitor()
    {
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                SharedPreferences.Editor editor=getSharedPreferences(getString(R.string.file_sharedPreferences_widget),MODE_PRIVATE).edit();
                editor.putBoolean(getString(R.string.name_auto_login),isChecked);
                editor.apply();
            }
        });
        layout_widget.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    startActivity(new Intent(SettingActivity.this, WidgetSettingActivity.class), ActivityOptions.makeSceneTransitionAnimation(SettingActivity.this).toBundle());
                } else
                {
                    startActivity(new Intent(SettingActivity.this, WidgetSettingActivity.class));
                }
            }
        });
        layout_about.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new AlertDialog.Builder(SettingActivity.this)
                        .setTitle(" ")
                        .setView(R.layout.dialog_about_us)
                        .setNegativeButton(getString(R.string.action_ok),null)
                        .show();
            }
        });
    }

}
