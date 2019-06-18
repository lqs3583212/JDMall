package com.example.jdmall01.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jdmall01.R;
import com.example.jdmall01.constant.IdiyMessage;
import com.example.jdmall01.controller.LoginController;
import com.example.jdmall01.listener.IModuleChangeListener;

public class LoginActivity extends BaseActivity implements IModuleChangeListener {
    private EditText mNameEt;
    private EditText mPwdEt;
    private LoginController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNameEt = findViewById(R.id.name_et);
        mPwdEt = findViewById(R.id.pwd_et);
        mController = new LoginController();
        mController.setIModuleChangeListener(this);


    }
    public void loginClick(View v) {
        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        // 判断用户名密码不为空
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)) {
            tip("用户名或密码不完整");
            return;
        }
        // 发送网络请求,请求数据
        mController.sendAsyncMessage(IdiyMessage.LOGIN_ACTION, name, pwd);

    }

    @Override
    public void onModuleChanged(int action, Object... values) {

    }
}
