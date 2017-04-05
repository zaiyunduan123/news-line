package jiang.newspaper.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 在云端 on 2017/4/1.
 */

public class ScrollViewPager extends ViewPager {


    public ScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ScrollViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }
}

