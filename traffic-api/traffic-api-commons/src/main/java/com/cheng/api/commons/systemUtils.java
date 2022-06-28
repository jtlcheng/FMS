package com.cheng.api.commons;

public class systemUtils {
    /**
     * 判断是否为空对象
     * @param object
     * @return true表示空
     */
    public static boolean isNull(Object object){
        return null==object;
    }

    /**
     * 判断字符串是否是空字符串
     * @param str
     * @return true表示空
     */
    public static boolean isNullOrEmpty(String str){
        return null==str || str.trim().equals("");
    }

}
