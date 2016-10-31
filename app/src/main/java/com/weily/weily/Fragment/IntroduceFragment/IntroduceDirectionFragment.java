package com.weily.weily.Fragment.IntroduceFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.weily.weily.R;

public class IntroduceDirectionFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.content_introduce_direction,container,false);
        RelativeLayout android_layout=(RelativeLayout)view.findViewById(R.id.android_layout);
        RelativeLayout unity_layout=(RelativeLayout)view.findViewById(R.id.unity_layout);
        RelativeLayout web_layout=(RelativeLayout)view.findViewById(R.id.web_layout);
        RelativeLayout art_layout=(RelativeLayout)view.findViewById(R.id.art_layout);
        return view;
    }
}
