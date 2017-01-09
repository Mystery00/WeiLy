package com.weily.weily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weily.weily.class_class.Usage;
import com.weily.weily.R;

import java.util.List;

public class UsageAdapter extends RecyclerView.Adapter<UsageAdapter.ViewHolder>
{
    private List<Usage> usageList;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView date;
        TextView income;
        TextView outcome;
        TextView over;
        TextView person;
        ViewHolder(View itemView)
        {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            income = (TextView) itemView.findViewById(R.id.income);
            outcome = (TextView) itemView.findViewById(R.id.outcome);
            over = (TextView) itemView.findViewById(R.id.over);
            person = (TextView) itemView.findViewById(R.id.person);
        }
    }

    public UsageAdapter(List<Usage> usageList)
    {
        this.usageList = usageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usage, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Usage usage = usageList.get(position);
        holder.date.setText(usage.getDate());
        holder.income.setText(usage.getIncome());
        holder.outcome.setText(usage.getOutcome());
        holder.over.setText(usage.getOver());
        holder.person.setText(usage.getPerson());
    }

    @Override
    public int getItemCount()
    {
        return usageList.size();
    }

}
