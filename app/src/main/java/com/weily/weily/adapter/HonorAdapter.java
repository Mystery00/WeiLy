package com.weily.weily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weily.weily.R;

import java.util.List;

public class HonorAdapter extends RecyclerView.Adapter<HonorAdapter.ViewHolder>
{
    private List<String> data;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View fullView;
        TextView textView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            fullView = itemView;
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public HonorAdapter(List<String> data)
    {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_honor, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.fullView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        String text = data.get(position);
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }
}
