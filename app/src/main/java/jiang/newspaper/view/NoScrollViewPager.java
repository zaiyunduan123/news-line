package jiang.newspaper.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 在云端 on 2017/3/25.
 */

public class NoScrollViewPager extends ViewPager {
    //在代码中实例化使用该方法
    public NoScrollViewPager(Context context) {
        super(context);
    }
    //在布局文件中使用该类，实例化该类用构造方法，没有该方法，软件会崩溃
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }


}
