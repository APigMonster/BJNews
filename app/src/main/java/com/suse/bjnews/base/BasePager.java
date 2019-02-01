package com.suse.bjnews.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.suse.bjnews.R;

public class BasePager {
    public final Context context;
    public View rootview;

    public ImageButton ib_menu;
    public TextView tv_title;
    public FrameLayout fl_base_page;

    public BasePager(Context context) {
        this.context = context;
        rootview = initView();
    }

    private View initView() {
        View view = View.inflate(context, R.layout.base_page, null);
        ib_menu = view.findViewById(R.id.ib_menu);
        tv_title = view.findViewById(R.id.tv_title);
        fl_base_page = view.findViewById(R.id.fl_base_page);
        return view;
    }

    public void initData() {

    }
}
