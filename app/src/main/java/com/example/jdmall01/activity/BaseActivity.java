package com.example.jdmall01.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import com.example.jdmall01.controller.BaseController;
import com.example.jdmall01.listener.IModuleChangeListener;


public abstract class  BaseActivity extends FragmentActivity implements IModuleChangeListener {

    protected BaseController mController;
    protected Handler mHandler = new Handler() {
//        @Override
        public void handleMessage(Message msg) {
//            super.handlerMessage(msg);
            BaseActivity.this.handlerMessage(msg);
        }
    };

    protected void handlerMessage(Message msg) {
        //defalut Empty implement
    }
    //初始化控制器
    protected  void initController() {
        //default Empty implement
    }
    //初始化UI
    protected abstract void initUI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    public void tip(String tipstr) {
        Toast.makeText(this, tipstr, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onModuleChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }


    protected boolean ifValueIsEmpty(String... values) {
        boolean flag = false;

        for (String value : values) {
            flag = TextUtils.isEmpty(value) || flag;
        }
        return flag;
    }


}
