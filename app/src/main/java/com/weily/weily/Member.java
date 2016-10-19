package com.weily.weily;

public class Member
{
    private String photoUrl;
    private String name;
    private String occupation;
    private String profession;
    private String college;
    private String phoneNumber;
    private String classNumber;

    public Member(String photoUrl, String name, String occupation, String profession, String college, String phoneNumber, String classNumber)
    {
        this.photoUrl=photoUrl;
        this.name = name;
        this.occupation = occupation;
        this.profession = profession;
        this.college = college;
        this.phoneNumber = phoneNumber;
        this.classNumber = classNumber;
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
}
