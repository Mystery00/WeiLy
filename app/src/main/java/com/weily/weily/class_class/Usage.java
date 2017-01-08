package com.weily.weily.class_class;

public class Usage
{
    private String date;//日期
    private String income;//收入
    private String outcome;//支出
    private String over;//余额
    private String person;//经手人

    public Usage(String date, String income, String outcome, String over, String person)
    {
        this.date = date;
        this.income = income;
        this.outcome = outcome;
        this.over = over;
        this.person = person;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getIncome()
    {
        return income;
    }

    public void setIncome(String income)
    {
        this.income = income;
    }

    public String getOutcome()
    {
        return outcome;
    }

    public void setOutcome(String outcome)
    {
        this.outcome = outcome;
    }

    public String getOver()
    {
        return over;
    }

    public void setOver(String over)
    {
        this.over = over;
    }

    public String getPerson()
    {
        return person;
    }

    public void setPerson(String person)
    {
        this.person = person;
    }
}
