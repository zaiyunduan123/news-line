package jiang.newspaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import jiang.newspaper.activity.MainActivity;
import jiang.newspaper.utils.CacheUtils;

public class SplashActivity extends Activity {
    public static final int ANIMATION_TIME=2000;
    public static final String IS_LOGGED_BEFORE="isLoggedBefore";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //欢迎字渐变动画
        AlphaAnimation aa=new AlphaAnimation(0.0f,1.0f);
        //渐变时间
        aa.setDuration(ANIMATION_TIME);
        //展示动画
        this.findViewById(R.id.tv_welcome).startAnimation(aa);

        aa.setAnimationListener(myAnimationListener);
    }

    Animation.AnimationListener myAnimationListener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
             //动画结束判断是否加入主页面
            Boolean isLoggedBefore;
            isLoggedBefore= CacheUtils.getBoolean(SplashActivity.this,IS_LOGGED_BEFORE);

            Intent intent=new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
//            if(isLoggedBefore)
//            {
//
//            }else
//            {
//
//            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
