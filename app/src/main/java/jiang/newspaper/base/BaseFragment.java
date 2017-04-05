package jiang.newspaper.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 在云端 on 2017/3/22.
 */

public abstract class BaseFragment extends Fragment {
    //得到activity
      public Context context;

    public BaseFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();//最终Fragment要绑定在MainActivity
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }
    //让孩子实现自己的视图，实现特有的效果
     public abstract View initView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();//数据绑定上面的视图
    }
    //如果自页面没有数据，联网请求数据，并且绑定到初始化initView的视图，有数据就直接绑定
    public  void initData(){

    }
}
