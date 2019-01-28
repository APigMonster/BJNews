package com.suse.bjnews.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.suse.bjnews.R;
import com.suse.bjnews.base.BaseFragment;

public class ContentFragment extends BaseFragment {

    private ViewPager vp_content_fragment;
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

        rg_content_fragment.check(R.id.rb_home);
    }
}
