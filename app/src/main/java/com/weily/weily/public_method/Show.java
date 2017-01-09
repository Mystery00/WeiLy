package com.weily.weily.public_method;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.weily.weily.callback.DateSetListener;

import java.util.Calendar;

public class Show
{
    public static void showDatePicker(Context context, Calendar calendar, final DateSetListener dateSetListener)
    {
        if (calendar == null)
        {
            calendar = Calendar.getInstance();
        }
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear + 1, dayOfMonth);
                dateSetListener.done(calendar);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, calendar.get(Calendar.DATE))
                .show();
    }
}
