package com.weily.weily.PublicMethod;

/**
 * Created by myste on 2016/11/14.
 * 判断两个对象是否相等
 */

public class Equal
{
    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
