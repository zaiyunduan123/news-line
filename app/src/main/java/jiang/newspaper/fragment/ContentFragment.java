package jiang.newspaper.fragment;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import jiang.newspaper.R;
import jiang.newspaper.activity.MainActivity;
import jiang.newspaper.adapter.MyContentPagerAdapter;
import jiang.newspaper.base.BaseFragment;
import jiang.newspaper.base.BasePager;
import jiang.newspaper.pager.HomePager;
import jiang.newspaper.pager.NewsCenterPager;
import jiang.newspaper.pager.SettingPager;
import jiang.newspaper.pager.SmartServicePager;
import jiang.newspaper.utils.LogUtil;
import jiang.newspaper.view.NoScrollViewPager;

/**
 * Created by 在云端 on 2017/3/23.
 */

public class ContentFragment extends BaseFragment {


    @ViewInject(R.id.rg_main)
    private RadioGroup rgMain;
    @ViewInject(R.id.view_pager_content)
    private NoScrollViewPager viewPager;

    public ArrayList<BasePager> basePagers;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_content, null);
        //1、把视图注入到框架中，让ContentFragment.this和View关联起来
        x.view().inject(ContentFragment.this, view);//Fragment类和视图关联起来


        return view;


    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.d("初始化主视图Data");

        basePagers = new ArrayList<>();
        //加入五个子pager
        basePagers.add(new HomePager(context));
        basePagers.add(new NewsCenterPager(context));
        basePagers.add(new SmartServicePager(context));
        basePagers.add(new SettingPager(context));


        viewPager.setAdapter(new MyContentPagerAdapter(basePagers));

        rgMain.setOnCheckedChangeListener(new MyOnCheckedchangeListener());

        //监听某个页面被选中，初始化对应的页面的数据
        viewPager.addOnPageChangeListener(new MyaddOnPageChangeListener());

        rgMain.check(R.id.rb_home);
        //因为刚进入的时候第一个页面是没有初始化的，所以要在初始化数据的时候调用第一个页面的initData（）
        basePagers.get(0).initData();
        //设置不能滑动
        isEnableSlidingMenu(false);
    }

    class MyaddOnPageChangeListener implements ViewPager.OnPageChangeListener {
        //当页面被滑动的时候被调用
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //页面跳转完再后调用
        @Override
        public void onPageSelected(int position) {
            basePagers.get(position).initData();
        }

        //状态被改变的时候调用
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnCheckedchangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.rb_home:
                    viewPager.setCurrentItem(0, false);//false设置没有抖的那种动画效果
//                     basePagers.get(0).initData();
                    isEnableSlidingMenu(false);
                    break;
                case R.id.rb_newscenter:
                    viewPager.setCurrentItem(1, false);
//                    basePagers.get(1).initData();
                    isEnableSlidingMenu(true);
                    break;
                case R.id.rb_smartservic:
                    viewPager.setCurrentItem(2, false);
//                    basePagers.get(2).initData();
                    isEnableSlidingMenu(false);
                    break;
                case R.id.rb_setting:
                    viewPager.setCurrentItem(3, false);
//                    basePagers.get(3).initData();
                    isEnableSlidingMenu(false);
                    break;
            }
        }
    }

    public void isEnableSlidingMenu(boolean isEnable) {
        if (isEnable == true) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    public NewsCenterPager getNewsCenterPager() {
        return (NewsCenterPager) basePagers.get(1);
    }
}




