package com.suse.bjnews.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.suse.bjnews.base.BaseFragment;

public class LeftMenuFragment extends BaseFragment {

    TextView textView;
    @Override
    public View initView() {
        textView = new TextView(content);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("fragment中设置的左边菜单");
    }
}
