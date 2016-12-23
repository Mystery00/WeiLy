package com.weily.weily.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.weily.weily.Adapter.UsageAdapter;
import com.weily.weily.Class.Usage;
import com.weily.weily.R;

import java.util.ArrayList;
import java.util.List;

public class UsageFragment extends Fragment
{
    private List<Usage> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_usage,container,false);
        ListView listView=(ListView)view.findViewById(R.id.listView);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        list.add(new Usage("16-10-24","+200","","500","哈哈哈"));
        list.add(new Usage("16-10-24","","-100","500","哈哈哈"));
        list.add(new Usage("16-10-24","+200","","500","哈哈哈"));
        list.add(new Usage("16-10-24","","-1000","500","哈哈哈"));
        list.add(new Usage("16-10-24","+200","","500","哈哈哈"));
        UsageAdapter adapter=new UsageAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                View dialog = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_new_usage, null);
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.title_dialog_new_usage)
                        .setView(dialog)
                        .setNegativeButton(getString(R.string.action_done), new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                            }
                        })
                        .show();
            }
        });
        return view;
    }
}
