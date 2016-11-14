package com.weily.weily.Activity.SettingActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.R;

public class SettingsActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private Switch sButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(R.layout.activity_settings);

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
        sButton = (Switch) findViewById(R.id.switch1);
        setSupportActionBar(toolbar);

        click();
    }


    public void click(){
        RelativeLayout rl1=(RelativeLayout)findViewById(R.id.content_settings_relativeLayoutOne);
        RelativeLayout rl2=(RelativeLayout)findViewById(R.id.content_settings_relativeLayoutTwo);
        RelativeLayout rl3=(RelativeLayout)findViewById(R.id.content_settings_relativeLayoutThree);
     /*   rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingsActivity.this,AutomaticLoginActivity.class);
                startActivity(intent);
            }
        });*/

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingsActivity.this,WidgetSettingsActivity.class);
                startActivity(intent);
            }
        });

        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(SettingsActivity.this,AboutUsActivity.class);
                //startActivity(intent);
                AlertDialog.Builder builder=new AlertDialog.Builder(SettingsActivity.this);
                builder.setIcon(R.mipmap.huaji);
                builder.setTitle("关于我们");
                final String[] information={"作者：邓易林","时间：2016.12.12","版本号：1.1.1"};
                builder.setItems(information, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(SettingsActivity.this,"关于："+information[i],Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });
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
        CompoundButton.OnCheckedChangeListener listener=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){

                }else {

                }
            }
        };
        sButton.setOnCheckedChangeListener(listener);
    }
}
