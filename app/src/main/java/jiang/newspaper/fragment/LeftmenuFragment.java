package jiang.newspaper.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import jiang.newspaper.R;
import jiang.newspaper.activity.MainActivity;
import jiang.newspaper.base.BaseFragment;
import jiang.newspaper.bean.NewsCenterPagerBean2;
import jiang.newspaper.pager.NewsCenterPager;
import jiang.newspaper.utils.DensityUtil;
import jiang.newspaper.utils.LogUtil;

/**
 * Created by 在云端 on 2017/3/22.
 */

public class LeftmenuFragment extends BaseFragment {

    private List<NewsCenterPagerBean2.DetailPagerData> data;
    private ListView listView;
    private int prePosition;
    private LeftmenuAdapter adapter;


    @Override
    public View initView() {
        LogUtil.d("初始化左菜单View");
        listView = new ListView(context);
        listView.setPadding(0, DensityUtil.dip2px(context, 40), 0, 0);//设置距离上部40px
        listView.setDividerHeight(0);//去掉分割线
        listView.setCacheColorHint(Color.TRANSPARENT);//默认点击会变灰，现在设置为透明
        listView.setSelection(android.R.color.transparent);//设置按下listView的item不变色

        return listView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.d("初始化左菜单Data");


        //设置item的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1、记录点击的位置，变红色
                prePosition = position;
                adapter.notifyDataSetChanged();//刷新适配器，会导致getCount（）---》getView（）
                //2、把左侧菜单关掉
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();
                swichPager(prePosition);

            }
        });
    }

    public void swichPager(int position) {
        MainActivity mainActivity = (MainActivity) context;
        ContentFragment contentFragment = mainActivity.getContentFragment();
        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
        newsCenterPager.swichPager(prePosition);
    }

    public void setData(List<NewsCenterPagerBean2.DetailPagerData> data) {
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            LogUtil.e("title=====" + data.get(i).getTitle());

        }
        //这里肯定有数据，所以在这里设置适配器
        adapter = new LeftmenuAdapter();
        listView.setAdapter(adapter);

        swichPager(prePosition);

    }

    class LeftmenuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(context, R.layout.item_leftmenu, null);
            textView.setText(data.get(position).getTitle());
//            if(position==prePosition) {
//                textView.setEnabled(true);
//            }else
//            {
//                textView.setEnabled(false);
//            }
            textView.setEnabled(position == prePosition ? true : false);
            return textView;
        }


    }
}
