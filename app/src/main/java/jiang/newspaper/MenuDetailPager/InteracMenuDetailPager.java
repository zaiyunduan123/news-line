package jiang.newspaper.MenuDetailPager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import jiang.newspaper.base.BaseMenuDetailPager;
import jiang.newspaper.utils.LogUtil;

/**
 * Created by 在云端 on 2017/4/1.
 */

public class InteracMenuDetailPager extends BaseMenuDetailPager {
    private TextView textView;
    public InteracMenuDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView=new TextView(context);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("互动详情页面");
        LogUtil.e("互动被初始化了");
    }
}
