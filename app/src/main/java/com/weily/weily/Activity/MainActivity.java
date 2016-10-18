package com.weily.weily.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.weily.weily.Fragment.HonorFragment;
import com.weily.weily.Fragment.IntroduceFragment.IntroduceFragment;
import com.weily.weily.Fragment.MemberFragment;
import com.weily.weily.Fragment.ResourcesFragment;
import com.weily.weily.Fragment.UsageFragment;
import com.weily.weily.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private Fragment fragment;

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
        fragmentManager = getSupportFragmentManager();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        showFragment(null,new IntroduceFragment());
    }

    private void monitor()
    {
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //noinspection deprecation
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void showFragment(Fragment from, Fragment to)
    {
        if (fragment != to)
        {
            fragment = to;
            FragmentTransaction transaction
                    = fragmentManager.beginTransaction();
            if(from==null)
            {
                transaction.show(to).commit();
            }else
            {
                transaction.hide(from).show(to).commit();
            }
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
        switch (item.getItemId())
        {
            case R.id.nav_introduce:
                showFragment(fragment,new IntroduceFragment());
                break;
            case R.id.nav_honor:
                showFragment(fragment,new HonorFragment());
                break;
            case R.id.nav_resources:
                showFragment(fragment,new ResourcesFragment());
                break;
            case R.id.nav_member:
                showFragment(fragment,new MemberFragment());
                break;
            case R.id.nav_usage:
                showFragment(fragment,new UsageFragment());
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
