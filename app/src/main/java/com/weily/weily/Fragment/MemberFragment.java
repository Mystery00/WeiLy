package com.weily.weily.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.weily.weily.Adapter.MemberAdapter;
import com.weily.weily.Class.Member;
import com.weily.weily.R;

import java.util.ArrayList;
import java.util.List;

public class MemberFragment extends Fragment
{
    private List<Member> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_member,container,false);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        list.add(new Member("","name","occupation","profession","college","111","classNumber"));
        list.add(new Member("","name1","occupation","profession","college","222","classNumber"));
        list.add(new Member("","name2","occupation","profession","college","333","classNumber"));
        MemberAdapter adapter=new MemberAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view1, int position, long id)
            {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                final Member member=list.get(position);
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_member,null);
                TextView name=(TextView)view.findViewById(R.id.text_name);
                TextView collage=(TextView)view.findViewById(R.id.text_collage);
                TextView class_number=(TextView)view.findViewById(R.id.text_class);
                TextView phone=(TextView)view.findViewById(R.id.text_phone);
                TextView profession=(TextView)view.findViewById(R.id.text_profession);
                TextView occupation=(TextView)view.findViewById(R.id.text_occupation);
                name.setText(member.getName());
                collage.setText(member.getCollege());
                class_number.setText(member.getClassNumber());
                phone.setText(member.getPhoneNumber());
                profession.setText(member.getProfession());
                occupation.setText(member.getOccupation());
                phone.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + member.getPhoneNumber()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                builder.setView(view);
                builder.setPositiveButton(R.string.action_done,null);
                builder.show();
            }
        });
        return view;
    }
}
