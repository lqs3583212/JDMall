package com.example.jdmall01.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.jdmall01.controller.BaseController;
import com.example.jdmall01.listener.IModuleChangeListener;

public abstract class BaseFragment extends Fragment implements IModuleChangeListener {
    protected BaseController mController;
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMessage(msg);
        }
    };



    protected abstract void initUI();

    protected void handleMessage(Message msg) {
        //defalut Empty implement
    }

    //初始化控制器
    protected void initController() {
        //default Empty implement
    }



    @Override
    public void onModuleChanged(int action, Object... values) {

    }


    public void tip(String tipStr) {
        Toast.makeText(getActivity(), tipStr, Toast.LENGTH_LONG).show();
    }

}

