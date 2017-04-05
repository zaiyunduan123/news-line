package jiang.newspaper.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jiang.newspaper.base.BasePager;

/**
 * Created by 在云端 on 2017/3/25.
 */

public class MyContentPagerAdapter extends PagerAdapter {
   private ArrayList<BasePager> basePagers;
    public MyContentPagerAdapter(ArrayList<BasePager> basePagers)
    {
        this.basePagers=basePagers;
    }

    @Override
    public int getCount() {
        return basePagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager basePager=basePagers.get(position);//各个子页面的实例
        View rootView=basePager.rootView;//代表各个子页面
        //调用各个页面的initData（）方法
            basePager.initData();

        container.addView(rootView);
        return rootView;//注意是返回rootView是该子页面

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}