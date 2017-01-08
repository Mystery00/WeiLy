package com.weily.weily.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.weily.weily.fragment.introduce_fragment.IntroduceDirectionFragment;
import com.weily.weily.fragment.introduce_fragment.IntroduceMainFragment;
import com.weily.weily.R;

public class IntroducePagerAdapter extends FragmentPagerAdapter
{
    private Context context;
    public IntroducePagerAdapter(FragmentManager fm,Context context)
    {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position+1)
        {
            case 1:
                return new IntroduceMainFragment();
            case 2:
                return new IntroduceDirectionFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String[] titles=context.getResources().getStringArray(R.array.introduce_titles);
        return titles[position];    }
}

