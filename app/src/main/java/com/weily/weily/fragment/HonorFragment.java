package com.weily.weily.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weily.weily.adapter.HonorAdapter;
import com.weily.weily.R;

import java.util.ArrayList;
import java.util.List;

public class HonorFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_honor, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        List<String> data = new ArrayList<>();
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        HonorAdapter adapter = new HonorAdapter(data);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
