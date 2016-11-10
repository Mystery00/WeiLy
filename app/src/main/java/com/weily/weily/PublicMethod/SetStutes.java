package com.weily.weily.PublicMethod;

import android.widget.EditText;

public class SetStutes
{
    public static void unClick(EditText editText)
    {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }

    public static void click(EditText editText)
    {
        editText.setFocusableInTouchMode(true);
        editText.setFocusable(true);
        editText.requestFocus();
    }
}
