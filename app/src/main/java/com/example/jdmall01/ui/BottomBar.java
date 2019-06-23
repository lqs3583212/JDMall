package com.example.jdmall01.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BottomBar extends LinearLayout {

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //1.渲染界面 当界面渲染完成
    //onFinishInflate 当所有控件测量排布完毕
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    //2.实现与用户的交互
}
