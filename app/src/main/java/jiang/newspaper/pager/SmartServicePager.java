package jiang.newspaper.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import jiang.newspaper.base.BasePager;
import jiang.newspaper.utils.LogUtil;

/**
 * Created by 在云端 on 2017/3/24.
 */

public class SmartServicePager extends BasePager {


    public SmartServicePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("商城数据被初始化...");

        tv_titile.setText("商城页面");

        TextView textView=new TextView(context);
        textView.setText("商城视图");
        ib_left_menu.setVisibility(View.GONE);
        //把子视图添加到BasePager的frameLayout
        fl_content.addView(textView);

    }
}

