package jiang.newspaper.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import jiang.newspaper.base.BasePager;
import jiang.newspaper.utils.LogUtil;

/**
 * Created by 在云端 on 2017/3/24.
 */

public class HomePager extends BasePager {


    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("主页面数据被初始化...");

        tv_titile.setText("主页面");

        TextView textView=new TextView(context);
        textView.setText("主页面视图");
        ib_left_menu.setVisibility(View.GONE);
        //把子视图添加到BasePager的frameLayout
        fl_content.addView(textView);

    }
}
