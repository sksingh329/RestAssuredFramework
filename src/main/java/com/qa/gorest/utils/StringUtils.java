package com.qa.gorest.utils;

public class StringUtils {

    public static String getRandomEmailId(String prefix, String suffix){
        return prefix+System.currentTimeMillis()+suffix;
    }
}
