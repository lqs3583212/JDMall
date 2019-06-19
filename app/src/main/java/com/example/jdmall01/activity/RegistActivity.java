package com.example.jdmall01.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.jdmall01.R;
import com.example.jdmall01.bean.RResult;
import com.example.jdmall01.controller.UserController;
import com.example.jdmall01.listener.IModuleChangeListener;

import static com.example.jdmall01.constant.IdiyMessage.*;

public class RegistActivity extends BaseActivity implements IModuleChangeListener {

    private EditText mNameEt;
    private EditText mPwdEt;
    private  EditText mSurePwdEt;
    private UserController mController;
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REGIST_ACTION_RESULT:
                    Log.e("123", "123");
                    handleRegistREsult((RResult)msg.obj);
            }
        }
    };

    private  void handleRegistREsult(RResult resultBean) {
        //如果注册成功 ---> 跳转到登录界面
        //如果注册失败 ---> 提示失败原因
        if (resultBean.isSuccess()) {
            //跳转到登录界面
            tip("注册成功");
            finish();
        } else {
            //提示原因
            tip("注册失败"+ resultBean.getErrorMsg());
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        //初始化控制器
        initController();
        //初始化UI
        initUI();
    }

    private void initController() {
        mController = new UserController();
        // 打开监听器,监听数据加载是否完毕
        mController.setIModuleChangeListener(this);
    }

    private void initUI() {
        mNameEt = findViewById(R.id.username_et);
        mPwdEt = findViewById(R.id.pwd_et);
        mSurePwdEt = findViewById(R.id.surepwd_et);
    }

    public void registClick(View v) {
        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        String surepwd = mSurePwdEt.getText().toString();

        if (TextUtils.isEmpty(name) ||
            TextUtils.isEmpty(pwd) ||
            TextUtils.isEmpty(surepwd)) {

            tip("请输入完整信息");
            return;
        }
        if (!pwd.equals(surepwd)) {
            tip("密码两次输入不一致");
            return;
        }
        mController.sendAsyncMessage(REGIST_ACTION, name, pwd);
//        Log.e("123", "123");
    }

    @Override
    public void onModuleChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
}


