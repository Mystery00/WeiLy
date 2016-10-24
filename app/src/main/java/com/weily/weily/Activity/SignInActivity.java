package com.weily.weily.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.weily.weily.PublicMethod.ExitApplication;
import com.weily.weily.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity
{
    final private static String pattern_username = "[A-Za-z0-9]+";
    final private static String pattern_password = "[A-Za-z0-9.]+";
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
        username_layout=(TextInputLayout)findViewById(R.id.usernameLayout);
        password_layout=(TextInputLayout)findViewById(R.id.passwordLayout);

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
                if(!Pattern.compile("[A-Za-z0-9]+").matcher(s.toString()).matches())
                {
                    username_layout.setError(getString(R.string.error_wrong_format));
                }else if(s.toString().length()<4||s.toString().length()>20)
                {
                    username_layout.setError(getString(R.string.error_out_of_size_username));
                }else
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
                if(!Pattern.compile("[A-Za-z0-9.]+").matcher(s.toString()).matches())
                {
                    password_layout.setError(getString(R.string.error_wrong_format));
                }else if(s.toString().length()<6||s.toString().length()>16)
                {
                    password_layout.setError(getString(R.string.error_out_of_size_password));
                }else
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
                if(isWrong())
                {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else
                {
                    Snackbar.make(view,"test",Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
    @SuppressWarnings("ConstantConditions")
    private boolean isWrong()
    {
        return !Pattern.compile("[A-Za-z0-9]+").matcher(username_layout.getEditText().getText().toString()).matches()
                ||!Pattern.compile("[A-Za-z0-9.]+").matcher(password_layout.getEditText().getText().toString()).matches()
                ||username_layout.getEditText().getText().length()<4
                ||username_layout.getEditText().getText().length()>20
                ||password_layout.getEditText().getText().length()<6
                ||password_layout.getEditText().getText().length()>16;
    }
}
