package com.weily.weily.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weily.weily.R;

import java.util.List;

public class HonorAdapter extends BaseAdapter
{
    private Context context;
    private List<String> data;
    private TextView textView;

    public HonorAdapter(Context context, List<String> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String honor = data.get(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_honor, null);
            textView = (TextView) convertView.findViewById(R.id.text);
        }
        textView.setText(honor);
        return convertView;
    }
}
