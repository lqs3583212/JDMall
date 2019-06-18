package com.example.jdmall01.controller;

import com.example.jdmall01.constant.IdiyMessage;
import com.example.jdmall01.constant.NetworkConst;
import com.example.jdmall01.util.NetworkUtil;

import java.util.HashMap;

public class LoginController extends BaseController {
    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.LOGIN_ACTION:
                login((String)values[0],(String)values[1]);
                break;


        }
    }

    private String login(String name, String pwd) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", name);
        params.put("username", pwd);
        String jsonStr = NetworkUtil.doPost(NetworkConst.Login_URL, params);

        //处理Json 使用fastjson 不能解析嵌套的对象 可以将一个嵌套的对象转换为Json

        return jsonStr;
    }
}
