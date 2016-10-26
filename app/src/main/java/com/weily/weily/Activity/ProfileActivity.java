package com.weily.weily.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.weily.weily.Class.User;
import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.PublicMethod.Logs;
import com.weily.weily.PublicMethod.SetStutes;
import com.weily.weily.R;

public class ProfileActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private TextView show_name;
    private EditText show_collage;
    private EditText show_class_number;
    private EditText show_phone_number;
    private EditText show_profession;
    private EditText show_occupation;
    private ImageView profile_background;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initialization();

        monitor();
    }

    private void initialization()
    {
        ExitApplication.getInstance().addActivity(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        show_name=(TextView)findViewById(R.id.text_name);
        show_collage=(EditText)findViewById(R.id.text_collage);
        show_class_number=(EditText)findViewById(R.id.text_class);
        show_phone_number=(EditText)findViewById(R.id.text_phone);
        show_profession=(EditText)findViewById(R.id.text_profession);
        show_occupation=(EditText)findViewById(R.id.text_occupation);
        profile_background=(ImageView)findViewById(R.id.profile_background);

        profile_background.setImageResource(R.mipmap.nav_background);

        Intent intent=getIntent();
        User user= (User) intent.getBundleExtra("user").getSerializable("user");
        if (user!=null)
        {
            show_name.setText(user.getName());
            show_collage.setText(user.getCollege());
            show_class_number.setText(user.getClassNumber());
            show_phone_number.setText(user.getPhoneNumber());
            show_profession.setText(user.getProfession());
            show_occupation.setText(user.getOccupation());
        }else
        {
            Logs.loge("获取为空!");
        }

        setSupportActionBar(toolbar);
    }

    private void monitor()
    {
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        toolbar.getMenu().findItem(R.id.action_edit).setVisible(true);
        SetStutes.unClick(show_collage);
        SetStutes.unClick(show_class_number);
        SetStutes.unClick(show_phone_number);
        SetStutes.unClick(show_profession);
        SetStutes.unClick(show_occupation);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_edit:
                SetStutes.click(show_collage);
                SetStutes.click(show_class_number);
                SetStutes.click(show_phone_number);
                SetStutes.click(show_profession);
                SetStutes.click(show_occupation);

                toolbar.getMenu().findItem(R.id.action_done).setVisible(true);
                toolbar.getMenu().findItem(R.id.action_edit).setVisible(false);
                break;
            case R.id.action_logout:
                break;
            case R.id.action_done:
                //执行更新个人资料操作
                SetStutes.unClick(show_collage);
                SetStutes.unClick(show_class_number);
                SetStutes.unClick(show_phone_number);
                SetStutes.unClick(show_profession);
                SetStutes.unClick(show_occupation);

                toolbar.getMenu().findItem(R.id.action_done).setVisible(false);
                toolbar.getMenu().findItem(R.id.action_edit).setVisible(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
