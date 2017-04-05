package jiang.newspaper.activity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import jiang.newspaper.R;
import jiang.newspaper.fragment.ContentFragment;
import jiang.newspaper.fragment.LeftmenuFragment;
import jiang.newspaper.utils.DensityUtil;

/**
 * Created by 在云端 on 2017/3/22.
 */

public class MainActivity extends SlidingFragmentActivity{
    public static final String CONTENT_FRAGMENT = "content_fragment";
    public static final String LEFTMENU_FRAGMENT ="leftmenu_fragment";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //设置主页面
        setContentView(R.layout.main_activity);

        //初始化SlidingMenu
        initSlidingMenu();
        //初始化Fragment
        initFragment();

    }

    private void initSlidingMenu() {
        //设置左侧菜单
        setBehindContentView(R.layout.activity_leftmenu);
        //
        SlidingMenu slidingMenu=getSlidingMenu();

        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置主页占据的宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this,200));
    }

    private void initFragment() {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.replace(R.id.ll_main_content,new ContentFragment(),CONTENT_FRAGMENT);//主页面
        transaction.replace(R.id.fl_leftmenu,new LeftmenuFragment(), LEFTMENU_FRAGMENT);//左侧菜单
        transaction.commit();

    }

    public LeftmenuFragment getLeftmenuFragment() {
        LeftmenuFragment leftmenu_fragment= (LeftmenuFragment) getSupportFragmentManager().findFragmentByTag(LEFTMENU_FRAGMENT);
        return leftmenu_fragment;
    }

    public ContentFragment getContentFragment() {
        ContentFragment contentFragment= (ContentFragment) getSupportFragmentManager().findFragmentByTag(CONTENT_FRAGMENT);
        return contentFragment;
    }
}
