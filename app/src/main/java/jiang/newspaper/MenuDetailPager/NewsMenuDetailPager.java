package jiang.newspaper.MenuDetailPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import jiang.newspaper.MenuDetailPager.Tabdetailpager.TabDetailPager;
import jiang.newspaper.R;
import jiang.newspaper.activity.MainActivity;
import jiang.newspaper.base.BaseMenuDetailPager;
import jiang.newspaper.bean.NewsCenterPagerBean2;
import jiang.newspaper.utils.LogUtil;

/**
 * Created by 在云端 on 2017/3/26.
 */

public class NewsMenuDetailPager extends BaseMenuDetailPager {


    //页签页面的集合-数据
    private List<NewsCenterPagerBean2.DetailPagerData.ChildrenData> children;
    //页签的集合-页面
    private ArrayList<TabDetailPager> tabDetailPagers;

    @ViewInject(R.id.viewpager_news_detail)
    private ViewPager viewpPager_detail;

    @ViewInject(R.id.tabpager_indicator)
    private TabPageIndicator tabPageIndicator;

    private int tempPosition = 0;

    public NewsMenuDetailPager(Context context, NewsCenterPagerBean2.DetailPagerData detailPagerData) {
        super(context);
        children = detailPagerData.getChildren();
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.newsmenu_detail_pager, null);
        x.view().inject(NewsMenuDetailPager.this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("新闻详情页面被初始化。。。");
//        tabDetailPagers = new ArrayList<>();
        //准备新闻详情页面的数据
        tabDetailPagers = new ArrayList<>();
        for(int i=0;i<children.size();i++){
            tabDetailPagers.add(new TabDetailPager(context,children.get(i)));
        }
        LogUtil.e(tabDetailPagers.size() + "");


        //设置适配器
        viewpPager_detail.setAdapter(new MyNewsMenuDetailPagerAdapter());

        //TabPageIndicator关联ViewPager
        tabPageIndicator.setViewPager(viewpPager_detail);

        //注意以后监听页面的变化，TabPageIndicator监听页面的变化
        tabPageIndicator.setOnPageChangeListener(new MyOnPageChangeListener());

        viewpPager_detail.setCurrentItem(tempPosition);
    }

    //监听器类
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
         if(position==0){
            isEnaleSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
         }else {
             isEnaleSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
         }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    //根据传入的参数设置是否让SlidingMenu可以滑动
    private void isEnaleSlidingMenu(int touchmodeFullscreen){
        MainActivity mainActivity= (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmodeFullscreen);
    }

    //viewpager的适配器类
    class MyNewsMenuDetailPagerAdapter extends PagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {
            return children.get(position).getTitle();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager  =tabDetailPagers.get(position);
            View rootView = tabDetailPager.rootView;
            tabDetailPager.initData();//初始化数据
            container.addView(rootView);
            return rootView;
        }

        @Override
        public int getCount() {
            return tabDetailPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }



}
