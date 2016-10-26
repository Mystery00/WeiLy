package com.weily.weily.Class;

import com.weily.weily.Callback.SignInListener;

import java.io.Serializable;

public class User implements Serializable
{
    private String username;
    private String password;
    private String photoUrl;
    private String name;
    private String occupation;
    private String profession;
    private String college;
    private String phoneNumber;
    private String classNumber;

    public User()
    {
    }

    public User(String photoUrl, String name, String occupation, String profession, String college, String phoneNumber, String classNumber)
    {
        this.photoUrl = photoUrl;//头像
        this.name = name;//姓名
        this.occupation = occupation;//方向
        this.profession = profession;//专业
        this.college = college;//学院
        this.phoneNumber = phoneNumber;//电话
        this.classNumber = classNumber;//班级
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

    public void login(SignInListener signInListener)
    {
        //执行登录操作
        signInListener.Success();
    }
}
