package com.weily.weily.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.weily.weily.Class.User;
import com.weily.weily.PublicMethod.BitmapLoad.DiskCache;
import com.weily.weily.R;

import java.util.List;

public class MemberAdapter extends BaseAdapter
{
    private class ViewHolder
    {
        ImageView photo;
        TextView name;
        TextView occupation;
        TextView profession;
    }

    private Context context;
    private List<User> userList;

    public MemberAdapter(Context context, List<User> userList)
    {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount()
    {
        return userList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        User member = userList.get(position);
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_member, null);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.item_photo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.occupation = (TextView) convertView.findViewById(R.id.item_occupation);
            viewHolder.profession = (TextView) convertView.findViewById(R.id.item_profession);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //给当前的ImageView设置Tag
        viewHolder.photo.setTag(userList.get(position).getPhotoUrl());
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new DiskCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(viewHolder.photo, R.mipmap.image_default, R.mipmap.image_faild);
        imageLoader.get(userList.get(position).getPhotoUrl(), listener);
        viewHolder.name.setText(member.getName());
        viewHolder.occupation.setText(member.getOccupation());
        viewHolder.profession.setText(member.getProfession());
        return convertView;
    }
}
