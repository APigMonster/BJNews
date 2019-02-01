package com.suse.bjnews.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.suse.bjnews.R;
import com.suse.bjnews.activity.MainActivity;
import com.suse.bjnews.base.BaseFragment;
import com.suse.bjnews.base.BasePager;
import com.suse.bjnews.pager.GovaffairPager;
import com.suse.bjnews.pager.HomePager;
import com.suse.bjnews.pager.NewsCenterPager;
import com.suse.bjnews.pager.SettingPager;
import com.suse.bjnews.pager.SmartServicePager;
import com.suse.bjnews.utils.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends BaseFragment {

    private NoScrollViewPager vp_content_fragment;
    private List<BasePager> data;
    private RadioGroup rg_content_fragment;

    @Override
    public View initView() {
        View view = View.inflate(content, R.layout.content_fragment, null);

        vp_content_fragment = view.findViewById(R.id.vp_content_fragment);
        rg_content_fragment = view.findViewById(R.id.rg_content_fragment);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //设置默认选中第一个radiobutton
        rg_content_fragment.check(R.id.rb_home);
        //初始化viewpage数据
        data = new ArrayList<>();
        data.add(new HomePager(content));
        data.add(new NewsCenterPager(content));
        data.add(new SmartServicePager(content));
        data.add(new GovaffairPager(content));
        data.add(new SettingPager(content));
        //设置适配器
        vp_content_fragment.setAdapter(new ContentFragmentAdapter());
        //设置RadioGroup监听
        rg_content_fragment.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置第一次选中
        data.get(0).initData();
    }
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home:
                    setPageShow(0, false);
                    break;
                case R.id.rb_newscenter:
                    setPageShow(1, true);
                    data.get(1).ib_menu.setVisibility(View.VISIBLE);
                    break;
                case R.id.rb_smartservice:
                    setPageShow(2, false);
                    break;
                case R.id.rb_govaffair:
                    setPageShow(3, false);
                    break;
                case R.id.rb_setting:
                    setPageShow(4, false);
                    break;
                default:
                    break;
                }
        }

        private void setPageShow(int i, boolean show) {
            vp_content_fragment.setCurrentItem(i);
            data.get(i).initData();
            MainActivity mainActivity = (MainActivity) content;
            if (show){
                mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
            }else {
                mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
            }

        }
    }
    class ContentFragmentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager basePager = data.get(position);
            //basePager.initData();
            View view = basePager.rootview;
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return o == view;
        }
    }
}
