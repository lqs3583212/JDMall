package com.example.jdmall01.activity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.example.jdmall01.JDApplication;
import com.example.jdmall01.R;
import com.example.jdmall01.bean.RLoginResult;
import com.example.jdmall01.bean.RResult;
import com.example.jdmall01.constant.IdiyMessage;
import com.example.jdmall01.controller.UserController;
import com.example.jdmall01.db.UserDao;
import com.example.jdmall01.util.ActivityUtil;

public class LoginActivity extends BaseActivity {
    private EditText mNameEt;
    private EditText mPwdEt;


    @Override
    protected void handlerMessage(Message msg) {
        super.handlerMessage(msg);
        switch (msg.what) {
            case IdiyMessage.LOGIN_ACTION_RESULT:
                handlerLoginResult(msg);
                break;
            case IdiyMessage.SAVE_USERTODB_RESULT:
                handleSaveUser2Db((boolean) msg.obj);
                break;
            case IdiyMessage.GET_USER_ACTION_RESULT:
                handlerGetUser(msg.obj);
                break;
        }
    }

    private void handlerGetUser(Object c) {
        if (c != null) {
            UserDao.UserInfo userInfo = (UserDao.UserInfo) c;
            mNameEt.setText(userInfo.name);
            mPwdEt.setText(userInfo.pwd);
        }

    }

    private void handleSaveUser2Db(boolean ifSuccess) {
        if (ifSuccess) {

            tip("密码已保存");
            ActivityUtil.start(this, MainActivity.class, true);
            Log.e("debug", "baocun");
        } else {
            tip("保存失败");
        }
    }


    private void handlerLoginResult(Message msg) {
        RResult rResult = (RResult) msg.obj;
//
//        此处判断石头登陆成功返回空指针,原因未知,为调试方便,暂时假定登陆成功
//        if (rResult.isSuccess()) {
//            //TODO 登陆成功
//            ActivityUtil.start(this, MainActivity.class,true);
//        }else tip("登陆失败" + rResult.getErrorMsg());

        //代码重构,将首页实现转移至保存用户名密码代码块
        //1. 将账号密码保存到数据库 传递账号密码
//        ActivityUtil.start(this, MainActivity.class,true);

        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        Log.e("name", name);
        mController.sendAsyncMessage(IdiyMessage.SAVE_USERTODB, name, pwd);

        // 将用户信息保存到Application
        RLoginResult bean = JSON.parseObject(rResult.getResult(), RLoginResult.class);
        ((JDApplication)getApplication()).setRLoginResult(bean);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initController();
        initUI();


    }

    public void loginClick(View v) {
        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        // 判断用户名密码不为空
        if (ifValueIsEmpty(name, pwd)) {
            tip("用户名或密码不完整");
            return;
        }
        // 发送网络请求,请求数据
        mController.sendAsyncMessage(IdiyMessage.LOGIN_ACTION, name, pwd);


    }

    public void registClick(View v) {
        ActivityUtil.start(this, RegistActivity.class, false);
    }



    @Override
    protected void initController() {
        mController = new UserController(this);
        mController.setIModuleChangeListener(this);
    }

    @Override
    protected void initUI() {
        mNameEt = findViewById(R.id.name_et);
        mPwdEt = findViewById(R.id.pwd_et);
        //如果数据库中有数据 --> 数据回显
        mController.sendAsyncMessage(IdiyMessage.GET_USER_ACTION, 0);
    }
}







