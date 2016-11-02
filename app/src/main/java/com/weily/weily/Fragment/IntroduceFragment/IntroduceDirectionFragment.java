package com.weily.weily.Fragment.IntroduceFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.weily.weily.Activity.IntroduceAndroidActivity;
import com.weily.weily.Activity.IntroduceArtActivity;
import com.weily.weily.Activity.IntroduceUnityActivity;
import com.weily.weily.Activity.IntroduceWebActivity;
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
        android_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    startActivity(new Intent(getActivity(), IntroduceAndroidActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }else
                {
                    startActivity(new Intent(getActivity(), IntroduceAndroidActivity.class));
                }
            }
        });
        unity_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    startActivity(new Intent(getActivity(), IntroduceUnityActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }else
                {
                    startActivity(new Intent(getActivity(), IntroduceUnityActivity.class));
                }
            }
        });
        web_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    startActivity(new Intent(getActivity(), IntroduceWebActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }else
                {
                    startActivity(new Intent(getActivity(), IntroduceWebActivity.class));
                }
            }
        });
        art_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    startActivity(new Intent(getActivity(), IntroduceArtActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }else
                {
                    startActivity(new Intent(getActivity(), IntroduceArtActivity.class));
                }
            }
        });
        return view;
    }
}
