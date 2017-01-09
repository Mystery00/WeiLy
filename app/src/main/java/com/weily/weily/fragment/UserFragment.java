package com.weily.weily.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weily.weily.adapter.UserAdapter;
import com.weily.weily.class_class.User;
import com.weily.weily.R;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment
{
    private List<User> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name", "occupation", "profession", "college", "111", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name1", "occupation", "profession", "college", "222", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",1));
        UserAdapter adapter=new UserAdapter(list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
