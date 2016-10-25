package com.weily.weily.Activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.weily.weily.Callback.SignInListener;
import com.weily.weily.Class.User;
import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.PublicMethod.Logs;
import com.weily.weily.R;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity
{
    final private static String pattern_username = "[A-Za-z0-9]+";
    final private static String pattern_password = "[A-Za-z0-9.]+";
    final private static int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 222;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextInputLayout username_layout;
    private TextInputLayout password_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initialization();

        monitor();
    }

    private void initialization()
    {
        ExitApplication.getInstance().addActivity(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        username_layout = (TextInputLayout) findViewById(R.id.usernameLayout);
        password_layout = (TextInputLayout) findViewById(R.id.passwordLayout);

        setSupportActionBar(toolbar);
    }

    @SuppressWarnings("ConstantConditions")
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
        username_layout.getEditText().addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!Pattern.compile(pattern_username).matcher(s.toString()).matches())
                {
                    username_layout.setError(getString(R.string.error_wrong_format));
                } else if (s.toString().length() < 4 || s.toString().length() > 20)
                {
                    username_layout.setError(getString(R.string.error_out_of_size_username));
                } else
                {
                    username_layout.setError(null);
                }
            }
        });
        password_layout.getEditText().addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!Pattern.compile(pattern_password).matcher(s.toString()).matches())
                {
                    password_layout.setError(getString(R.string.error_wrong_format));
                } else if (s.toString().length() < 6 || s.toString().length() > 16)
                {
                    password_layout.setError(getString(R.string.error_out_of_size_password));
                } else
                {
                    password_layout.setError(null);
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!getSharedPreferences("permission",MODE_PRIVATE).getBoolean("WRITE_EXTERNAL_STORAGE",false))
                {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(SignInActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                }
                if (!isWrong())
                {
                    final User user = new User();
                    user.setUsername(username_layout.getEditText().getText().toString());
                    user.setPassword(password_layout.getEditText().getText().toString());
                    user.login(new SignInListener()
                    {
                        @Override
                        public void Success()
                        {
                            //登陆成功保存登录信息
                            SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                            sharedPreferences.edit()
                                    .putString("username", username_layout.getEditText().getText().toString())
                                    .putString("password", password_layout.getEditText().getText().toString())
                                    .apply();
                            finish();
                        }

                        @Override
                        public void Failure(int code, String message)
                        {
                            Logs.loge(code, message);//打印错误信息
                        }
                    });
                } else
                {
                    Logs.loge(getString(R.string.error_wrong_format));
                    Snackbar.make(view, getString(R.string.error_wrong_format), Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    @SuppressWarnings("ConstantConditions")
    private boolean isWrong()
    {
        return !Pattern.compile(pattern_username).matcher(username_layout.getEditText().getText().toString()).matches()
                || !Pattern.compile(pattern_password).matcher(password_layout.getEditText().getText().toString()).matches()
                || username_layout.getEditText().getText().length() < 4
                || username_layout.getEditText().getText().length() > 20
                || password_layout.getEditText().getText().length() < 6
                || password_layout.getEditText().getText().length() > 16;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("permission", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                editor.putBoolean("WRITE_EXTERNAL_STORAGE", true);
            } else
            {
                editor.putBoolean("WRITE_EXTERNAL_STORAGE", false);
            }
            editor.apply();
        }
    }
}
