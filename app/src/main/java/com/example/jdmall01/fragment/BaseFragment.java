package com.example.jdmall01.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;


import com.example.jdmall01.controller.BaseController;

public class BaseFragment extends Fragment {
    protected BaseController mController;
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMessage(msg);
        }
    };

    protected void handleMessage(Message msg) {
        //defalut Empty implement
    }

    //初始化控制器
    protected void initController() {
        //default Empty implement
    }
}
