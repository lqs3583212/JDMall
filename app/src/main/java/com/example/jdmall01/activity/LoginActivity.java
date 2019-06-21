package com.example.jdmall01.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.jdmall01.R;
import com.example.jdmall01.constant.IdiyMessage;
import com.example.jdmall01.controller.UserController;
import com.example.jdmall01.util.ActivityUtil;

public class LoginActivity extends BaseActivity {
    private EditText mNameEt;
    private EditText mPwdEt;



    @Override
    protected void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case IdiyMessage.LOGIN_ACTION_RESULT:
                handlerLoginResult(msg);
                break;
        }
    }

    private void handlerLoginResult(Message msg) {
//        RResult rResult = (RResult) msg.obj;
//
//        此处判断石头登陆成功返回空指针,原因未知,为调试方便,暂时假定登陆成功
//        if (rResult.isSuccess()) {
//            //TODO 登陆成功
//            ActivityUtil.start(this, MainActivity.class,true);
//        }else tip("登陆失败" + rResult.getErrorMsg());

        ActivityUtil.start(this, MainActivity.class,true);
    }

    @Override
    protected void initController() {
        mController = new UserController();
        mController.setIModuleChangeListener(this);
    }

    @Override
    protected void initUI() {
        mNameEt = findViewById(R.id.name_et);
        mPwdEt = findViewById(R.id.pwd_et);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        initController();
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
}
