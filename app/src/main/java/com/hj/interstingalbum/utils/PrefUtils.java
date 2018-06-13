package com.hj.interstingalbum.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    public static void setPrefBoolean(Context context,String name,String key,boolean value){
        SharedPreferences.Editor editor= context.getSharedPreferences(name,Context.MODE_PRIVATE).edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public static boolean getPrefBoolean(Context context,String name,String key){
        SharedPreferences spf= context.getSharedPreferences(name,Context.MODE_PRIVATE);
        return spf.getBoolean(key,false);
    }

}
