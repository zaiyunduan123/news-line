package jiang.newspaper.pager;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import jiang.newspaper.MenuDetailPager.InteracMenuDetailPager;
import jiang.newspaper.MenuDetailPager.NewsMenuDetailPager;
import jiang.newspaper.MenuDetailPager.PhotosMenuDetailPager;
import jiang.newspaper.MenuDetailPager.TopicMenuDetailPager;
import jiang.newspaper.activity.MainActivity;
import jiang.newspaper.base.BaseMenuDetailPager;
import jiang.newspaper.base.BasePager;
import jiang.newspaper.bean.NewsCenterPagerBean2;
import jiang.newspaper.fragment.LeftmenuFragment;
import jiang.newspaper.utils.CacheUtils;
import jiang.newspaper.utils.Constants;
import jiang.newspaper.utils.LogUtil;

/**
 * Created by 在云端 on 2017/3/24.
 */

public class NewsCenterPager extends BasePager {

    //    private ArrayList<BaseMenuDetailPager> detailPagers;
    List<NewsCenterPagerBean2.DetailPagerData> data;
    ArrayList<BaseMenuDetailPager> detailBasePagers;

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("新闻中心数据被初始化...");
        ib_left_menu.setVisibility(View.VISIBLE);
        ib_left_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();
            }
        });

        tv_titile.setText("新闻中心页面");

        TextView textView = new TextView(context);
        textView.setText("新闻中心视图");
        //把子视图添加到BasePager的frameLayout
        fl_content.addView(textView);

        //在联网之前请求数据缓存数据
        String saveJson = CacheUtils.getString(context, Constants.NEWSCENTER_PAGER_URL);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }
        getDataFromNet();

    }

    private void getDataFromNet() {
        RequestParams params = new RequestParams(Constants.NEWSCENTER_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网请求成功。。。。。" + result);
                //缓存数据
                CacheUtils.putString(context, Constants.NEWSCENTER_PAGER_URL, result);
                processData(result);//解析数据
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网请求失败。。。。。" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("联网请求撤销。。。。。" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("联网请求完成。。。。。");
            }
        });

    }

    private void processData(String json) {


        NewsCenterPagerBean2 bean = parsedJson(json);
        String title = bean.getData().get(0).getChildren().get(0).getTitle();
        LogUtil.e("Gson解析Json.....title" + title);

//        detailPagers=new ArrayList<>();
//        detailPagers.add(new NewsMenuDetailPager(context));
        data = bean.getData();

        MainActivity mainActivity = (MainActivity) context;

        LeftmenuFragment leftmenuFragment = mainActivity.getLeftmenuFragment();

        detailBasePagers = new ArrayList<>();


        detailBasePagers.add(new NewsMenuDetailPager(context,data.get(0)));
        detailBasePagers.add(new TopicMenuDetailPager(context));
        detailBasePagers.add( new PhotosMenuDetailPager(context));
        detailBasePagers.add(new InteracMenuDetailPager(context));

        //给左侧菜单传数据
        leftmenuFragment.setData(data);








    }

    //根据位置切换到对应的详情页面
    public void swichPager(int position){
        //1、添加标题
        tv_titile.setText(data.get(position).getTitle());//根据详情页面修改标题
        //2、移除之前内容
        fl_content.removeAllViews();
        detailBasePagers.get(position).initData();
        fl_content.addView(detailBasePagers.get(position).rootView);

    }

    private NewsCenterPagerBean2 parsedJson(String json) {
        Gson gson = new Gson();
        NewsCenterPagerBean2 bean = gson.fromJson(json, NewsCenterPagerBean2.class);
        return bean;
    }

}