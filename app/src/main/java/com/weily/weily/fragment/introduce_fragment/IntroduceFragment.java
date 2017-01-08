package com.weily.weily.fragment.introduce_fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weily.weily.adapter.IntroducePagerAdapter;
import com.weily.weily.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IntroduceFragment extends Fragment {



    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.content)
    ViewPager content;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduce, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {
        IntroducePagerAdapter introducePagerAdapter = new IntroducePagerAdapter(getActivity().getSupportFragmentManager(), getActivity().getApplicationContext());
        content.setAdapter(introducePagerAdapter);
        tabLayout.setupWithViewPager(content);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
