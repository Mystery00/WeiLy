package com.weily.weily.Fragment.IntroduceFragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weily.weily.Adapter.IntroducePagerAdapter;
import com.weily.weily.R;

public class IntroduceFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_introduce,container,false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        ViewPager content = (ViewPager) view.findViewById(R.id.content);

        IntroducePagerAdapter introducePagerAdapter
                = new IntroducePagerAdapter(getActivity().getSupportFragmentManager(),getActivity().getApplicationContext());
        content.setAdapter(introducePagerAdapter);
        tabLayout.setupWithViewPager(content);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }
}
