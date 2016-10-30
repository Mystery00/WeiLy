package com.weily.weily.Fragment.IntroduceFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.weily.weily.R;

import java.util.Timer;
import java.util.TimerTask;

public class IntroduceMainFragment extends Fragment
{
    private static int time=7;
    private Toast toast;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.content_introduce_main,container,false);
        ImageView img=(ImageView)view.findViewById(R.id.logo);
        Button button=(Button)view.findViewById(R.id.button);
        img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(time>=0)
                {
                    if (time == 0)
                    {
                        new AlertDialog.Builder(getContext())
                                .setMessage("There are no eggs! ")
                                .setView(R.layout.eggs)
                                .setPositiveButton(getString(R.string.action_done),null)
                                .show();
                    } else
                    {
                        toast=Toast.makeText(getContext(),"Please click more "+time+" times!",Toast.LENGTH_SHORT);
                        toast.show();
                        new Timer().schedule(new TimerTask()
                        {
                            @Override
                            public void run()
                            {
                                toast.cancel();
                            }
                        }, 100);
                        time--;
                    }
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://git-sublime.github.io/test/weily/");
                intent.setData(content_url);
                startActivity(intent);
            }
        });
        return view;
    }
}
