package com.suse.bjnews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    public Activity content;//因为在MainActivity中创建，所以content是MainActivity

    /**
     * 当Fragment被创建时被调用
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = getActivity();
    }

    /**
     * 当视图被创建时被调用
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 不同子类有不同的视图，所以由子类调用设置视图
     * @return
     */
    public abstract View initView() ;

    /**
     * 当activity被创建后被调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    /**
     * 空方法，可由子类实现；
     * 为视图设置数据
     */
    public void initData(){

    }
}
