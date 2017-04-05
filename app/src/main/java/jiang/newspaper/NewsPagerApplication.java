package jiang.newspaper;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 在云端 on 2017/3/24.
 * 代表整个软件
 */

public class NewsPagerApplication extends Application {
    //所有组件被创建之前执行
    @Override
    public void onCreate() {
        super.onCreate();
        //xUtils3的初始化
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
