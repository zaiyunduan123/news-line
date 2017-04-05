package jiang.newspaper.base;

import android.content.Context;
import android.view.View;

/**
 * Created by 在云端 on 2017/3/26.
 * 详情页面的基类
 */

public abstract class BaseMenuDetailPager {
    //上下文
    public final Context context;

    //代表各个详情页面的视图
    public View rootView;

    public BaseMenuDetailPager(Context context) {
        this.context = context;
        rootView = initView();
    }

    //抽象方法，让孩子必须实现该方法，每个页面实现不同的效果
    public abstract View initView();

    //子页面需要绑定数据、联网请求数据的时候，重写该方法
    public void initData() {

    }
}
