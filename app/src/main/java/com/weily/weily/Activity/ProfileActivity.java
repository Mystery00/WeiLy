package com.weily.weily.Activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.weily.weily.PublicMethod.BitmapLoad.DiskCache;
import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.PublicMethod.SetStutes;
import com.weily.weily.R;

public class ProfileActivity extends AppCompatActivity
{
    final private static String HEADURL = "head_url";
    final private static String NAME = "name";
    final private static String COLLAGE = "collage";
    final private static String CLASS_NUMBER = "class_number";
    final private static String PHONE_NUMBER = "phone_number";
    final private static String PROFESSION = "profession";
    final private static String OCCUPATION = "occupation";
    final private static int DOWNLOAD = 233;
    private Toolbar toolbar;
    private TextView show_name;
    private EditText show_collage;
    private EditText show_class_number;
    private EditText show_phone_number;
    private EditText show_profession;
    private EditText show_occupation;
    private ImageView profile_background;

    /**
     * 解决子线程加载试图问题
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == DOWNLOAD)
            {
                profile_background.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(R.layout.activity_profile);

        initialization();

        monitor();
    }

    private void initialization()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
        ExitApplication.getInstance().addActivity(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        show_name = (TextView) findViewById(R.id.text_name);
        show_collage = (EditText) findViewById(R.id.text_collage);
        show_class_number = (EditText) findViewById(R.id.text_class);
        show_phone_number = (EditText) findViewById(R.id.text_phone);
        show_profession = (EditText) findViewById(R.id.text_profession);
        show_occupation = (EditText) findViewById(R.id.text_occupation);
        profile_background = (ImageView) findViewById(R.id.profile_background);

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
    protected void onResume()
    {
        super.onResume();

        final SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        show_name.setText(sharedPreferences.getString(NAME, ""));
        show_collage.setText(sharedPreferences.getString(COLLAGE, ""));
        show_class_number.setText(sharedPreferences.getString(CLASS_NUMBER, ""));
        show_phone_number.setText(sharedPreferences.getString(PHONE_NUMBER, ""));
        show_profession.setText(sharedPreferences.getString(PROFESSION, ""));
        show_occupation.setText(sharedPreferences.getString(OCCUPATION, ""));


        RequestQueue requestQueue = Volley.newRequestQueue(ProfileActivity.this);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new DiskCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(null, R.mipmap.image_default, R.mipmap.image_faild);
        imageLoader.get(getString(R.string.url_show_page), listener);
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    Bitmap bitmap = ImageLoader.getImage(sharedPreferences.getString(HEADURL, "http://git-sublime.github.io/test/weily/picture/logo.png"));
//                    Message message = new Message();
//                    message.what = DOWNLOAD;
//                    message.obj = bitmap;
//                    handler.sendMessage(message);
//                } catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
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
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
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
                //执行登出操作
                editor.remove("username");
                editor.remove("password");
                editor.remove("name");
                editor.remove("collage");
                editor.remove("class_number");
                editor.remove("phone_number");
                editor.remove("profession");
                editor.remove("occupation");
                finish();
                break;
            case R.id.action_done:
                //执行更新个人资料操作
                editor.putString("collage", show_collage.getText().toString());
                editor.putString("class_number", show_class_number.getText().toString());
                editor.putString("phone_number", show_phone_number.getText().toString());
                editor.putString("profession", show_profession.getText().toString());
                editor.putString("occupation", show_occupation.getText().toString());

                SetStutes.unClick(show_collage);
                SetStutes.unClick(show_class_number);
                SetStutes.unClick(show_phone_number);
                SetStutes.unClick(show_profession);
                SetStutes.unClick(show_occupation);

                toolbar.getMenu().findItem(R.id.action_done).setVisible(false);
                toolbar.getMenu().findItem(R.id.action_edit).setVisible(true);
                break;
        }
        editor.apply();
        return super.onOptionsItemSelected(item);
    }
}
