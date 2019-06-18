package com.example.jdmall01.controller;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.jdmall01.activity.LoginActivity;
import com.example.jdmall01.bean.RResult;
import com.example.jdmall01.constant.IdiyMessage;
import com.example.jdmall01.constant.NetworkConst;
import com.example.jdmall01.listener.IModuleChangeListener;
import com.example.jdmall01.util.NetworkUtil;

import java.util.HashMap;

public class LoginController extends BaseController {

    private IModuleChangeListener mListener;

    public void setIModuleChangeListener(IModuleChangeListener listener) {
        mListener = listener;
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.LOGIN_ACTION:
                RResult rResult = login((String) values[0], (String) values[1]);

                //告诉Activity数据加载完成
                mListener.onModuleChanged(IdiyMessage.LOGIN_ACTION_RESULT, rResult);
                break;


        }
    }

    private RResult login(String name, String pwd) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", name);
        params.put("username", pwd);
        String jsonStr = NetworkUtil.doPost(NetworkConst.Login_URL, params);

        //处理Json 使用fastjson 不能解析嵌套的对象 可以将一个嵌套的对象转换为Json

        return JSON.parseObject(jsonStr, RResult.class);

    }


}
