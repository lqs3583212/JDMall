package com.example.jdmall01;

import android.app.Application;

import com.example.jdmall01.bean.RLoginResult;

public class JDApplication extends Application {

    public RLoginResult mRLoginResult;

    public void setRLoginResult(RLoginResult bean) {
        mRLoginResult = bean;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
