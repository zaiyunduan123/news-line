package jiang.newspaper.MenuDetailPager.Tabdetailpager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import jiang.newspaper.base.BaseMenuDetailPager;
import jiang.newspaper.bean.NewsCenterPagerBean2;

/**
 * Created by 在云端 on 2017/3/28.
 */

public class TabDetailPager extends BaseMenuDetailPager {
    private NewsCenterPagerBean2.DetailPagerData.ChildrenData children;
    private TextView textView;
    public TabDetailPager(Context context, NewsCenterPagerBean2.DetailPagerData.ChildrenData children) {
        super(context);
        this.children=children;
    }

    @Override
    public View initView() {
        textView=new TextView(context);

        return textView;
    }

    @Override
    public void initData() {
        textView.setText(children.getTitle());
    }
}
