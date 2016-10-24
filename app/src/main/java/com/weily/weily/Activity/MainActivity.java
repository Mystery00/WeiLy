package com.weily.weily.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.weily.weily.Fragment.HonorFragment;
import com.weily.weily.Fragment.IntroduceFragment.IntroduceFragment;
import com.weily.weily.Fragment.UserFragment;
import com.weily.weily.Fragment.ResourcesFragment;
import com.weily.weily.Fragment.UsageFragment;
import com.weily.weily.PublicMethod.CircleImageView;
import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener
{
    private Toolbar toolbar;
    private NavigationView navigationView;
    private CircleImageView head;
    private TextView username;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private IntroduceFragment introduceFragment;
    private HonorFragment honorFragment;
    private ResourcesFragment resourcesFragment;
    private UserFragment memberFragment;
    private UsageFragment usageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        monitor();
    }

    private void initialization()
    {
        ExitApplication.getInstance().addActivity(this);
        fragmentManager = getSupportFragmentManager();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        head=(CircleImageView)headerLayout.findViewById(R.id.nav_head);
        username=(TextView)headerLayout.findViewById(R.id.nav_username);
        setSupportActionBar(toolbar);

        /**
         * 默认显示introduce
         */
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        if (introduceFragment == null)
        {
            introduceFragment = new IntroduceFragment();
            fragmentTransaction.add(R.id.fragment, introduceFragment);
        } else
        {
            fragmentTransaction.show(introduceFragment);
        }
        fragmentTransaction.commit();
    }

    private void monitor()
    {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //noinspection deprecation
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        head.setOnClickListener(this);
        username.setOnClickListener(this);

    }

    public void hideFragments(FragmentTransaction fragmentTransaction)
    {
        if (introduceFragment != null)
        {
            fragmentTransaction.hide(introduceFragment);
        }
        if (honorFragment != null)
        {
            fragmentTransaction.hide(honorFragment);
        }
        if (memberFragment != null)
        {
            fragmentTransaction.hide(memberFragment);
        }
        if (resourcesFragment != null)
        {
            fragmentTransaction.hide(resourcesFragment);
        }
        if (usageFragment != null)
        {
            fragmentTransaction.hide(usageFragment);
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (item.getItemId())
        {
            case R.id.nav_introduce:
                hideFragments(fragmentTransaction);
                if (introduceFragment == null)
                {
                    introduceFragment = new IntroduceFragment();
                    fragmentTransaction.add(R.id.fragment, introduceFragment);
                } else
                {
                    fragmentTransaction.show(introduceFragment);
                }
                break;
            case R.id.nav_honor:
                hideFragments(fragmentTransaction);
                if (honorFragment == null)
                {
                    honorFragment = new HonorFragment();
                    fragmentTransaction.add(R.id.fragment, honorFragment);
                } else
                {
                    fragmentTransaction.show(honorFragment);
                }
                break;
            case R.id.nav_resources:
                hideFragments(fragmentTransaction);
                if (resourcesFragment == null)
                {
                    resourcesFragment = new ResourcesFragment();
                    fragmentTransaction.add(R.id.fragment, resourcesFragment);
                } else
                {
                    fragmentTransaction.show(resourcesFragment);
                }
                break;
            case R.id.nav_member:
                hideFragments(fragmentTransaction);
                if (memberFragment == null)
                {
                    memberFragment = new UserFragment();
                    fragmentTransaction.add(R.id.fragment, memberFragment);
                } else
                {
                    fragmentTransaction.show(memberFragment);
                }
                break;
            case R.id.nav_usage:
                hideFragments(fragmentTransaction);
                if (usageFragment == null)
                {
                    usageFragment = new UsageFragment();
                    fragmentTransaction.add(R.id.fragment, usageFragment);
                } else
                {
                    fragmentTransaction.show(usageFragment);
                }
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
            case R.id.nav_exit:
                ExitApplication.getInstance().exit();
                break;
        }
        fragmentTransaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v)
    {
        //判断跳转
        startActivity(new Intent(MainActivity.this,SignInActivity.class));
    }
}
