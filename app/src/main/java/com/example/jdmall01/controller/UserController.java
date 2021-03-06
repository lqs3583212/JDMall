package com.example.jdmall01.controller;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.jdmall01.bean.RResult;
import com.example.jdmall01.constant.IdiyMessage;
import com.example.jdmall01.constant.NetworkConst;
import com.example.jdmall01.db.UserDao;
import com.example.jdmall01.util.AESUtils;
import com.example.jdmall01.util.NetworkUtil;

import java.util.HashMap;

public class UserController extends BaseController {

    private String secretKey = AESUtils.generateKey();

    private UserDao mUserDao;

    public UserController(Context c) {
        super(c);
        mUserDao = new UserDao(mContext);

    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.LOGIN_ACTION:
//                RResult rResult = login((String) values[0], (String) values[1]);

                //代码重构
                RResult rResult = loginOrRegist(NetworkConst.Login_URL, (String) values[0], (String) values[1]);
                //告诉Activity数据加载完成
                mListener.onModuleChanged(IdiyMessage.LOGIN_ACTION_RESULT, rResult);
                break;

            case IdiyMessage.REGIST_ACTION:

//                RResult rResult = regist((String) values[0], (String) values[1]);

                //代码重构
                RResult loginOrRegist = loginOrRegist(NetworkConst.Regist_URL, (String) values[0], (String) values[1]);
                mListener.onModuleChanged(IdiyMessage.LOGIN_ACTION_RESULT, loginOrRegist);
                break;

            case IdiyMessage.SAVE_USERTODB:
                boolean saveUser2Db = saveUser2Db((String) values[0], (String) values[1]);
                Log.e("value0", (String) values[0]);
                mListener.onModuleChanged(IdiyMessage.SAVE_USERTODB_RESULT, saveUser2Db);
                break;
            case IdiyMessage.GET_USER_ACTION:
                UserDao.UserInfo userInfo = aquireUser();
                //可能会报空指针
                mListener.onModuleChanged(IdiyMessage.GET_USER_ACTION_RESULT, userInfo);
                break;
            case IdiyMessage.CLEAR_USER_ACTION:
                clearUser();
                mListener.onModuleChanged(IdiyMessage.GET_USER_ACTION_RESULT, 0);
                break;
        }
    }

    private void clearUser() {
        mUserDao.clearUsers();
    }

    private UserDao.UserInfo aquireUser() {
        UserDao.UserInfo userInfo = mUserDao.aquireLastestUser();
        if (userInfo != null) {
            try {
                Log.e("AES", "decrypt1");
                userInfo.name = AESUtils.decrypt(secretKey, userInfo.name);
                Log.e("AES", "decrypt");
                userInfo.pwd = AESUtils.decrypt(secretKey, userInfo.pwd);
            } catch (Exception e) {
                e.printStackTrace();
            }


            Log.e("AES", userInfo.name + "," + userInfo.pwd);

            return userInfo;

        }
        return null;


    }

    private boolean saveUser2Db(String name, String pwd) {
//        mUserDao.clearUsers();

        //可逆性加密 AES
        try {
            name = AESUtils.encrypt(secretKey, name);
            Log.e("encryptname", name);
            pwd = AESUtils.encrypt(secretKey, pwd);
            Log.e("encryptpwd", pwd);
        } catch (Exception e) {
            Log.e("encrypt", "error");
            e.printStackTrace();

        }
        Log.e("encrypt", "encryptok");

        return mUserDao.saveUser(name, pwd);
    }

    //重构regist和login方法
    private RResult loginOrRegist(String url, String name, String pwd) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", name);
        params.put("username", pwd);
        String jsonStr = NetworkUtil.doPost(url, params);
        return JSON.parseObject(jsonStr, RResult.class);
    }

//    private RResult regist(String name, String pwd) {
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("username", name);
//        params.put("username", pwd);
//       loginOrRegist(NetworkConst.Regist_URL, (String) values[0], (String) values[1]); String jsonStr = NetworkUtil.doPost(NetworkConst.Login_URL, params);
//        return JSON.parseObject(jsonStr, RResult.class);
//    }
//
//    private RResult login(String name, String pwd) {
//        HashMap<String, String> params = new HashMap<>();
//        params.put("username", name);
//        params.put("username", pwd);
//        String jsonStr = NetworkUtil.doPost(NetworkConst.Login_URL, params);
//
//        //处理Json 使用fastjson 不能解析嵌套的对象 可以将一个嵌套的对象转换为Json
//
//        return JSON.parseObject(jsonStr, RResult.class);
//
//    }


}
