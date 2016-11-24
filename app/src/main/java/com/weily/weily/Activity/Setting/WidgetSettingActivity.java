package com.weily.weily.Activity.Setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.R;

public class WidgetSettingActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private RelativeLayout refresh_time;
    private RelativeLayout refresh_now;
    private RelativeLayout click_event;
    private RelativeLayout text_color;
    private RelativeLayout text_alignment;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(R.layout.activity_widget_setting);

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
        sharedPreferences = getSharedPreferences(getString(R.string.file_sharedPreferences_widget), MODE_PRIVATE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        refresh_time = (RelativeLayout) findViewById(R.id.layout_refresh_time);
        refresh_now = (RelativeLayout) findViewById(R.id.layout_refresh);
        click_event = (RelativeLayout) findViewById(R.id.layout_click);
        text_color = (RelativeLayout) findViewById(R.id.layout_color);
        text_alignment = (RelativeLayout) findViewById(R.id.layout_alignment);
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
        refresh_time.setOnClickListener(new View.OnClickListener()
        {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onClick(View v)
            {
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                View view = LayoutInflater.from(WidgetSettingActivity.this).inflate(R.layout.dialog_refresh_time, null);
                final TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(R.id.text_time);
                textInputLayout.getEditText().setText(String.valueOf(sharedPreferences.getLong(getString(R.string.name_widget_refresh_time),300000)/60000));
                new AlertDialog.Builder(WidgetSettingActivity.this)
                        .setTitle(" ")
                        .setView(view)
                        .setNegativeButton(R.string.action_cancel, null)
                        .setPositiveButton(R.string.action_done, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                //noinspection ConstantConditions
                                long time = Long.parseLong(textInputLayout.getEditText().getText().toString()) * 60000;
                                editor.putLong(getString(R.string.name_widget_refresh_time), time);
                                editor.apply();
                            }
                        })
                        .show();
            }
        });
        refresh_now.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(sharedPreferences.getBoolean(getString(R.string.name_widget_is_enabled),false))
                {
                    Intent intent = new Intent();
                    intent.setAction("com.Hitokoto.text");
                    sendBroadcast(intent);
                    Snackbar.make(v, getString(R.string.hint_broadcast), Snackbar.LENGTH_SHORT)
                            .show();
                }else
                {
                    Snackbar.make(v, getString(R.string.hint_disabled), Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
