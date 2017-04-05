package jiang.newspaper.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 在云端 on 2017/3/21.
 * 缓存软件的一些参数和数据
 */

public class CacheUtils {
    //缓存boolean类型
    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("NewsMessage", Context.MODE_PRIVATE);
        //没有找该key就返回false
        return sp.getBoolean(key, false);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("NewsMessage", Context.MODE_PRIVATE);
         sp.edit().putBoolean(key,value).commit();
        //没有找该key就返回false
    }

    //缓存文本
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("NewsMessage", Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    //缓存boolean类型
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("NewsMessage", Context.MODE_PRIVATE);
        //没有找该key就返回false
        return sp.getString(key,"");
    }

}
