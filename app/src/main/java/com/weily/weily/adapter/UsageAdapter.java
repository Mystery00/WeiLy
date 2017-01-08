package com.weily.weily.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weily.weily.class_class.Usage;
import com.weily.weily.R;

import java.util.List;

public class UsageAdapter extends BaseAdapter
{
    private class ViewHolder
    {
        TextView date;
        TextView income;
        TextView outcome;
        TextView over;
        TextView person;
    }

    private Context context;
    private List<Usage> usageList;

    public UsageAdapter(Context context, List<Usage> usageList)
    {
        this.context=context;
        this.usageList=usageList;
    }
    @Override
    public int getCount()
    {
        return usageList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return usageList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Usage usage=usageList.get(position);
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_usage,null);
            viewHolder.date=(TextView)convertView.findViewById(R.id.date);
            viewHolder.income=(TextView)convertView.findViewById(R.id.income);
            viewHolder.outcome=(TextView)convertView.findViewById(R.id.outcome);
            viewHolder.over=(TextView)convertView.findViewById(R.id.over);
            viewHolder.person=(TextView)convertView.findViewById(R.id.person);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.date.setText(usage.getDate());
        viewHolder.income.setText(usage.getIncome());
        viewHolder.outcome.setText(usage.getOutcome());
        viewHolder.over.setText(usage.getOver());
        viewHolder.person.setText(usage.getPerson());
        return convertView;
    }
}
