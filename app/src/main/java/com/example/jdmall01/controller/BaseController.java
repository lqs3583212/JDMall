package com.example.jdmall01.controller;

import android.content.Context;

import com.example.jdmall01.listener.IModuleChangeListener;

public abstract class BaseController {

    protected IModuleChangeListener mListener;
    protected Context mContext;

    public void setIModuleChangeListener(IModuleChangeListener listener) {
        mListener = listener;
    }

    public BaseController(Context c) {
        mContext = c;
    }

    //一个页面可能有多个网络请求,用来区别网络请求
    // values 请求的数据
    public void sendAsyncMessage(final int action, final Object... values) {
        new Thread() {
            public void run() {
                handleMessage(action, values);
            }
        }.start();

    }

    //子类实现业务
    protected abstract void handleMessage(int action,Object... values);
}
