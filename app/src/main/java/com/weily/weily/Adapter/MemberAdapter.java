package com.weily.weily.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weily.weily.Member;
import com.weily.weily.R;

import java.util.List;

public class MemberAdapter extends BaseAdapter
{
    private Context context;
    private List<Member> memberList;
    private ImageView photo;
    private TextView name;
    private TextView occupation;
    private TextView profession;

    public MemberAdapter(Context context,List<Member> memberList)
    {
        this.context=context;
        this.memberList=memberList;
    }

    @Override
    public int getCount()
    {
        return memberList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return memberList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Member member=memberList.get(position);
        if (convertView == null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_member,null);
            photo=(ImageView)convertView.findViewById(R.id.item_photo);
            name=(TextView)convertView.findViewById(R.id.item_name);
            occupation=(TextView)convertView.findViewById(R.id.item_occupation);
            profession=(TextView)convertView.findViewById(R.id.item_profession);
        }
        name.setText(member.getName());
        occupation.setText(member.getOccupation());
        profession.setText(member.getProfession());
        return convertView;
    }
}
