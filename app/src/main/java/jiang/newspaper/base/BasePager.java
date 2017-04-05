package jiang.newspaper.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import jiang.newspaper.R;

/**
 * Created by 在云端 on 2017/3/24.
 * 公共类
 */

public class BasePager {

    public final Context context;//这个上下文是MainActivity

    public View rootView;//视图。代表各个不同的页面

    public TextView tv_titile;//显示标题

    public ImageButton ib_left_menu;//点击侧滑

    public FrameLayout fl_content;//再加各个子页面
    public BasePager(Context context)
    {
        this.context=context;
        rootView=initView();//调用构造方法初始化view
    }

    private  View initView() {
        //基类的页面
        View view=View.inflate(context, R.layout.base_pager,null);
        tv_titile= (TextView) view.findViewById(R.id.tv_title);
        ib_left_menu= (ImageButton) view.findViewById(R.id.ib_left_menu);
        fl_content= (FrameLayout) view.findViewById(R.id.fl_content);

        return view;
    }

//初始化数据，当孩子需要初始化数据，或者绑定数据；联网请求数据并且绑定的时候，重写该方法
    public void initData()
    {

    }

}
