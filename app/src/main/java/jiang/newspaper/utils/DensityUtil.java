package jiang.newspaper.utils;

import android.content.Context;

/**
 * Created by 在云端 on 2017/3/22.
 * 单位转换工具
 */

public class DensityUtil {
    //手机分辨率从dip的单位转换为px（像素）
    public static int dip2px(Context context ,float dpValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
    //手机分辨率从px（像素）的单位转换为dp
    public static int px2dip(Context context,float pxValue)
    {
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }

}
