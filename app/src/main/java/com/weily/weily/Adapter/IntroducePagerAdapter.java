package com.weily.weily.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.weily.weily.Fragment.IntroduceFragment.IntroduceDirectionFragment;
import com.weily.weily.Fragment.IntroduceFragment.IntroduceMainFragment;
import com.weily.weily.Fragment.IntroduceFragment.IntroduceOccupationFragment;
import com.weily.weily.R;

public class IntroducePagerAdapter extends FragmentPagerAdapter
{
    final int PAGE_COUNT=3;
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
            case 3:
                return new IntroduceOccupationFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String[] titles=context.getResources().getStringArray(R.array.introduce_titles);
        return titles[position];
    }
}

