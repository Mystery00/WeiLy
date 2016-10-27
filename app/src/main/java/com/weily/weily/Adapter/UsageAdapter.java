package com.weily.weily.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weily.weily.Class.Usage;
import com.weily.weily.R;

import java.util.List;

public class UsageAdapter extends BaseAdapter
{
    private Context context;
    private List<Usage> usageList;
    private TextView date;
    private TextView income;
    private TextView outcome;
    private TextView over;
    private TextView person;

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
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_usage,null);
            date=(TextView)convertView.findViewById(R.id.date);
            income=(TextView)convertView.findViewById(R.id.income);
            outcome=(TextView)convertView.findViewById(R.id.outcome);
            over=(TextView)convertView.findViewById(R.id.over);
            person=(TextView)convertView.findViewById(R.id.person);
        }
        date.setText(usage.getDate());
        income.setText(usage.getIncome());
        outcome.setText(usage.getOutcome());
        over.setText(usage.getOver());
        person.setText(usage.getPerson());

        return convertView;
    }
}
