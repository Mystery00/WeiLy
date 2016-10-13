package com.weily.weily.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.weily.weily.R;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter
{
    private Context context;
    private String[] titles;
    public final int COUNT ;
    public MyFragmentPagerAdapter(FragmentManager fm,Context context)
    {
        super(fm);
        this.context=context;
        titles=context.getResources().getStringArray(R.array.main_fragment);
        COUNT= titles.length;
    }

    @Override
    public int getCount()
    {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position)
    {
        return null;
    }
}
