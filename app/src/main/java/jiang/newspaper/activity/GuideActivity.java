package jiang.newspaper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import jiang.newspaper.R;

/**
 * Created by 在云端 on 2017/3/21.
 */

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private ArrayList<ImageView> imageViews;//图片资源的集合
    private LinearLayout ll;//放置圆点
    //最后一页的按钮
    private Button btnMain;

    //实例化圆点View
    private ImageView pointView;
    private ImageView[] pointViews;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
      //初始化页面
        initViews();
      //初始化底部小点
        initDocts();

    }

    private void initDocts() {
        ll= (LinearLayout) findViewById(R.id.ll_pager);
        //根据ViewPager的item数量来实例化数组
        pointViews=new ImageView[imageViews.size()];
        for(int i=0;i<pointViews.length;i++)
        {
            pointView=new ImageView(this);
            //设置pointView的参数
            LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(20,20);
            //除了第一个，都距离左原点10个像素
            if(i!=0)
            {
                param.leftMargin=10;
            }
            pointView.setLayoutParams(param);
            pointViews[i]=pointView;
            if(i==0)
            {
                pointView.setBackgroundResource(R.drawable.doc_red);
            }else
            {
                pointView.setBackgroundResource(R.drawable.doc_grey);
            }
            ll.addView(pointViews[i]);
        }
    }


    private void initViews()
    {
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        btnMain = (Button)findViewById(R.id.btn_enter_main);
        ll= (LinearLayout) findViewById(R.id.ll_pager);

        int[] imgs=new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };
        imageViews=new ArrayList<>();
        for(int i=0;i<imgs.length;i++)
        {
            ImageView imageView=new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(imgs[i]);
            imageViews.add(imageView);


        }
        //设置ViewPager的Adapter
        viewPager.setAdapter(new ViewPagerAdapter());
        //设置ViewPager的滑动监听
        viewPager.setOnPageChangeListener(this);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
          for(int i=0;i<imageViews.size();i++)
          {
              //是当前页面就
              pointViews[position].setBackgroundResource(R.drawable.doc_red);
              if(position!=i)
              {
                  pointViews[i].setBackgroundResource(R.drawable.doc_grey);
              }
          }
          //判断是否是最后一页，若是则显示按钮
        if(position==imageViews.size()-1)
        {
            btnMain.setVisibility(View.VISIBLE);
        }else {
            btnMain.setVisibility(View.GONE);
        }

        btnMain.setOnClickListener(new MyOnCLickListner());
    }
    class MyOnCLickListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(GuideActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ViewPagerAdapter extends PagerAdapter{

        //删除viewpager的item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
               container.removeView(imageViews.get(position));
        }


        //获得当前界面数
        @Override
        public int getCount() {
            if(imageViews!=null)
            {
                return imageViews.size();
            }
            return 0;
        }
        //加载viewpager的每一个item
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //添加到容器
            container.addView(imageViews.get(position),0);
            return imageViews.get(position);
        }
        /*
           判断当前页面view是不是加载的那个item
           view 当前创建的页面
           object：instantiateItem方法返回的结果
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

}
