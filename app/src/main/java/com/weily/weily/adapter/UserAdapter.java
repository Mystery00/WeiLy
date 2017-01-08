package com.weily.weily.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.weily.weily.class_class.User;
import com.weily.weily.public_method.Equal;
import com.weily.weily.public_method.bitmap_load.DiskCache;
import com.weily.weily.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
{

    private List<User> userList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View fullView;
        ImageView photo;
        TextView name;
        TextView occupation;
        TextView profession;

        ViewHolder(View itemView)
        {
            super(itemView);
            fullView = itemView;
            photo = (ImageView) itemView.findViewById(R.id.item_photo);
            name = (TextView) itemView.findViewById(R.id.item_name);
            occupation = (TextView) itemView.findViewById(R.id.item_occupation);
            profession = (TextView) itemView.findViewById(R.id.item_profession);
        }
    }

    public UserAdapter(List<User> userList)
    {
        this.userList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.fullView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final User user = userList.get(viewHolder.getAdapterPosition());
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_show_member, null);
                ImageView head = (ImageView) view.findViewById(R.id.show_head);
                TextView name = (TextView) view.findViewById(R.id.text_name);
                TextView collage = (TextView) view.findViewById(R.id.text_collage);
                TextView class_number = (TextView) view.findViewById(R.id.text_class);
                TextView phone = (TextView) view.findViewById(R.id.text_phone);
                TextView profession = (TextView) view.findViewById(R.id.text_profession);
                TextView occupation = (TextView) view.findViewById(R.id.text_occupation);
                if (!Equal.equals(user.getPhotoUrl(), ""))
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    ImageLoader imageLoader = new ImageLoader(requestQueue, new DiskCache());
                    ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(head, R.mipmap.image_default, R.mipmap.image_faild);
                    imageLoader.get(user.getPhotoUrl(), imageListener);
                }
                name.setText(user.getName());
                collage.setText(user.getCollege());
                class_number.setText(user.getClassNumber());
                phone.setText(user.getPhoneNumber());
                profession.setText(user.getProfession());
                occupation.setText(user.getOccupation());
                phone.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + user.getPhoneNumber()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                builder.setView(view);
                builder.setPositiveButton(R.string.action_done, null);
                builder.show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        User user = userList.get(position);
        //给当前的ImageView设置Tag
        holder.photo.setTag(userList.get(position).getPhotoUrl());
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new DiskCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.photo, R.mipmap.image_default, R.mipmap.image_faild);
        imageLoader.get(userList.get(position).getPhotoUrl(), listener);
        holder.name.setText(user.getName());
        holder.occupation.setText(user.getOccupation());
        holder.profession.setText(user.getProfession());
    }

    @Override
    public int getItemCount()
    {
        return userList.size();
    }
}
