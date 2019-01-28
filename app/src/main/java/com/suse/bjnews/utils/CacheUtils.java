package com.suse.bjnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {


    public static boolean getBoolean(Context context, String key) {

        SharedPreferences spf = context.getSharedPreferences("MyBJNews", Context.MODE_PRIVATE);
        return  spf.getBoolean(key, false);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences spf = context.getSharedPreferences("MyBJNews", Context.MODE_PRIVATE);
        spf.edit().putBoolean(key, value).commit();
    }
}
