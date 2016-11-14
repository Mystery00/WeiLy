package com.weily.weily.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weily.weily.Adapter.HonorAdapter;
import com.weily.weily.R;

import java.util.ArrayList;
import java.util.List;

public class HonorFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_honor,container,false);
        ListView listView=(ListView)view.findViewById(R.id.listView);
        List<String> data=new ArrayList<>();
        data.add("sad");
        data.add("sad");
        data.add("sad");
        data.add("fsdgfasrg");
        data.add("sad");
        data.add("测试中文");
        data.add("sad");
        data.add("sad");
        data.add("s哈哈哈");
        data.add("s测试123456468");
        HonorAdapter adapter=new HonorAdapter(getContext(),data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

            }
        });
        return view;
    }
}
