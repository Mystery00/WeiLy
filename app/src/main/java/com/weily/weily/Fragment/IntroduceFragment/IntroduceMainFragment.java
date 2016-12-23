package com.weily.weily.Fragment.IntroduceFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.weily.weily.R;

public class IntroduceMainFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.content_introduce_main,container,false);
        ImageView img=(ImageView)view.findViewById(R.id.logo);
        Button button=(Button)view.findViewById(R.id.button);
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
