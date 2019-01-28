package com.suse.bjnews.activity;

import android.app.Activity;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.suse.bjnews.R;
import com.suse.bjnews.utils.DensityUtil;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置主页面
        setContentView(R.layout.activity_main);
        //设置左边菜单
        setBehindContentView(R.layout.activity_leftmenu);
        //设置显示模式
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置滑动模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置主页面占用宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this, 270));
    }
}
