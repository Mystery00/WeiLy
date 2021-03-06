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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.weily.weily.Adapter.MemberAdapter;
import com.weily.weily.Class.User;
import com.weily.weily.PublicMethod.BitmapLoad.DiskCache;
import com.weily.weily.PublicMethod.Equal;
import com.weily.weily.R;

import java.util.ArrayList;
import java.util.List;

public class MemberFragment extends Fragment
{
    private List<User> list=new ArrayList<>();
    private ImageView head;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_member,container,false);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name", "occupation", "profession", "college", "111", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name1", "occupation", "profession", "college", "222", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        list.add(new User("http://git-sublime.github.io/test/weily/picture/logo.png", "name2", "occupation", "profession", "college", "333", "classNumber",false));
        MemberAdapter adapter=new MemberAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view1, int position, long id)
            {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                final User user = list.get(position);
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_member,null);
                head = (ImageView) view.findViewById(R.id.show_head);
                TextView name=(TextView)view.findViewById(R.id.text_name);
                TextView collage=(TextView)view.findViewById(R.id.text_collage);
                TextView class_number=(TextView)view.findViewById(R.id.text_class);
                TextView phone=(TextView)view.findViewById(R.id.text_phone);
                TextView profession=(TextView)view.findViewById(R.id.text_profession);
                TextView occupation=(TextView)view.findViewById(R.id.text_occupation);
                if (!Equal.equals(user.getPhotoUrl(), ""))
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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
