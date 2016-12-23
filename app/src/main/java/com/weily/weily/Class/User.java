package com.weily.weily.Class;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.weily.weily.Callback.SignInListener;
import com.weily.weily.R;

import org.json.JSONException;
import org.json.JSONObject;

public class User
{
    private static final String TAG = "User";
    private String username;
    private String password;
    private String photoUrl;
    private String name;
    private String occupation;
    private String profession;
    private String college;
    private String phoneNumber;
    private String classNumber;
    private boolean isManager;

    public User()
    {
    }

    public User(String photoUrl, String name, String occupation, String profession, String college, String phoneNumber, String classNumber, boolean isManager)
    {
        this.photoUrl = photoUrl;//头像
        this.name = name;//姓名
        this.occupation = occupation;//方向
        this.profession = profession;//专业
        this.college = college;//学院
        this.phoneNumber = phoneNumber;//电话
        this.classNumber = classNumber;//班级
        this.isManager = isManager;//管理员标识
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhotoUrl()
    {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOccupation()
    {
        return occupation;
    }

    public void setOccupation(String occupation)
    {
        this.occupation = occupation;
    }

    public String getProfession()
    {
        return profession;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public String getCollege()
    {
        return college;
    }

    public void setCollege(String college)
    {
        this.college = college;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getClassNumber()
    {
        return classNumber;
    }

    public void setClassNumber(String classNumber)
    {
        this.classNumber = classNumber;
    }

    public boolean getIsManager()
    {
        return isManager;
    }

    public void setIsManager(boolean isManager)
    {
        this.isManager = isManager;
    }

    public void login(Context context, final SignInListener signInListener)
    {
        //执行登录操作
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(context.getString(R.string.url_login) + "username=kone&password=" + password, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject jsonObject)
            {
                try
                {
                    if (jsonObject.getString("data").equals("1"))
                    {
                        Log.i(TAG, "onResponse: 登陆成功");
                        signInListener.Success();
                    } else
                    {
                        signInListener.Failure("Username or password error!");
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                volleyError.printStackTrace();
                signInListener.Failure("test");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public User getCurrectUser()
    {
        return null;
    }
}
