package com.example.jdmall01.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jdmall01.R;
import com.example.jdmall01.activity.MainActivity;
import com.example.jdmall01.listener.IBottomBarClickListener;

public class BottomBar extends LinearLayout implements View.OnClickListener {

    private View mHomeIv;
    private View mHomeTv;
    private View mCategoryIv;
    private View mCategoryTv;
    private View mShopcarTv;
    private View mShopcarIv;
    private View mMyJDIv;
    private View mMyJDTv;
    private IBottomBarClickListener mListener;

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //1.渲染界面 当界面渲染完成
    //onFinishInflate 当所有控件测量排布完毕会被调用
    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        findViewById(R.id.frag_main_ll).setOnClickListener(this);
        findViewById(R.id.frag_category_ll).setOnClickListener(this);
        findViewById(R.id.frag_shopcar_ll).setOnClickListener(this);
        findViewById(R.id.frag_mine_ll).setOnClickListener(this);
        mHomeIv = findViewById(R.id.frag_main_iv);
        mHomeTv = findViewById(R.id.frag_main);
        mCategoryIv = findViewById(R.id.frag_category_iv);
        mCategoryTv = findViewById(R.id.frag_category);
        mShopcarTv = findViewById(R.id.frag_shopcar);
        mShopcarIv = findViewById(R.id.frag_shopcar_iv);
        mMyJDIv = findViewById(R.id.frag_mine_iv);
        mMyJDTv = findViewById(R.id.frag_mine);
    }

    //2.实现与用户的交互
    @Override
    public void onClick(View v) {
        //实现点击修改底部栏图标颜色
        mHomeIv.setSelected(v.getId()==R.id.frag_main_ll);
        mHomeTv.setSelected(v.getId()==R.id.frag_main_ll);
        mCategoryIv.setSelected(v.getId()==R.id.frag_category_ll);
        mCategoryTv.setSelected(v.getId()==R.id.frag_category_ll);
        mShopcarIv.setSelected(v.getId()==R.id.frag_shopcar_ll);
        mShopcarTv.setSelected(v.getId()==R.id.frag_shopcar_ll);
        mMyJDIv.setSelected(v.getId()==R.id.frag_mine_ll);
        mMyJDTv.setSelected(v.getId()==R.id.frag_mine_ll);
        }

    public void setIBottomBarClickListener(IBottomBarClickListener listener) {
        mListener=listener;
    }
}



