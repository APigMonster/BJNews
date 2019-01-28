package com.suse.bjnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.suse.bjnews.utils.CacheUtils;
import com.suse.bjnews.utils.DensityUtil;
import com.suse.bjnews.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private ViewPager vp_guide;
    private List<ImageView> data = new ArrayList<>();

    private LinearLayout ll_point_group;
    private int width_pdi;
    private ImageView iv_red_point;
    private int leftMax;
    private Button btn_into_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        vp_guide = findViewById(R.id.vp_guide);
        ll_point_group = findViewById(R.id.ll_point_group);
        iv_red_point = findViewById(R.id.iv_red_point);
        btn_into_main = findViewById(R.id.btn_into_main);

        //初始化数据
        width_pdi = DensityUtil.dip2px(GuideActivity.this, 10);
        initData();

        //设置适配器
        vp_guide.setAdapter(new GuideAdapter());

        //保证红点已经测量完成
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new GuideGlobalLayoutListener());

        //获取页面滑动的百分比
        //addOnPageChangeListener() 而不是 setOnxxxxListener()
        vp_guide.addOnPageChangeListener(new GuideOnPageChangeListener());

    }

    public void intoMain(View v){

        //保存已经进入过主界面的状态
        CacheUtils.putBoolean(GuideActivity.this, WelcomeActivity.START_MAIN, true);

        startActivity(new Intent(GuideActivity.this, MainActivity.class));
        finish();
    }

    class GuideOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

            int leftMargin = (int) (i * leftMax + v * leftMax);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftMargin;
            iv_red_point.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int i) {
            if (i == data.size() - 1) {
                btn_into_main.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    class GuideGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            //此方法不止执行一次
            //this -> GuideGlobalLayoutListener类
            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            //间距
            leftMax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
        }
    }

    private void initData() {
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(ids[i]);
            data.add(imageView);

            Log.e("TAG", width_pdi + "转化像素后");
            //添加点
            ImageView point = new ImageView(GuideActivity.this);
            point.setBackgroundResource(R.drawable.point_nomal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width_pdi, width_pdi);
            if (i != 0) {
                params.leftMargin = width_pdi;
            }
            point.setLayoutParams(params);
            ll_point_group.addView(point);
        }
    }

    /**
     * (第一次)进入第一个页面：
     * instantiateItem() 方法被执行 position=0
     * instantiateItem() 方法被执行 position=1
     * 进入第二个页面(向右滑动) :
     * instantiateItem() 方法被执行 position=2
     * 进入第三个页面(向右滑动) :
     * destroyItem() 方法被执行 position=0
     * 根据上面总结：
     * 刚进入viewpager的时候，他会加载第一屏和第二屏，展示第一屏，预加载第二屏，
     * 滑动到第二屏的时候，会预加载第三屏而第一屏因为有可能滑动回第一屏，所以不会销毁，
     * 而滑动到第三屏，就会销毁第一屏，第二屏不会销毁，同理第四屏也是这样
     */
    class GuideAdapter extends PagerAdapter {
        /**
         * 把image添加到容器中
         *
         * @param container viewPage
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            Log.e("TAG", "instantiateItem() 方法被执行 position=" + position);
            ImageView imageView = data.get(position);
            container.addView(imageView);
            return imageView;
            //return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            Log.e("TAG", "destroyItem() 方法被执行 position=" + position);
            //container.removeView((View) object);
            container.removeView(data.get(position));

        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return o == view;
        }
    }

}
