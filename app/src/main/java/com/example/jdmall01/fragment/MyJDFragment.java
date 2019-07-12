package com.example.jdmall01.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jdmall01.JDApplication;
import com.example.jdmall01.R;
import com.example.jdmall01.activity.LoginActivity;
import com.example.jdmall01.bean.RLoginResult;
import com.example.jdmall01.constant.IdiyMessage;
import com.example.jdmall01.controller.UserController;
import com.example.jdmall01.util.ActivityUtil;

public class MyJDFragment extends BaseFragment implements View.OnClickListener {

    private TextView mUserNameTv;
    private TextView mWaitPayTv;
    private TextView mWaitReciveTv;

    @Override
    protected void handlerMessage(Message msg) {
        super.handlerMessage(msg);
        switch (msg.what) {
            case IdiyMessage.CLEAR_USER_ACTION_RESULT:
                ActivityUtil.start(getActivity(), LoginActivity.class, true);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myjd, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initUI();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                mController.sendAsyncMessage(IdiyMessage.CLEAR_USER_ACTION, 0);
                break;
        }
    }

    @Override
    protected void initController() {
        mController = new UserController(getActivity());
        mController.setIModuleChangeListener(this);
    }

    @Override
    protected void initUI() {
        getActivity().findViewById(R.id.logout_btn).setOnClickListener(this);

        mUserNameTv =(TextView) getActivity().findViewById(R.id.user_name_tv);
        TextView mUserLevelTv = (TextView) getActivity().findViewById(R.id.user_level_tv);
        mWaitPayTv =(TextView) getActivity().findViewById(R.id.wait_pay_tv);
        mWaitReciveTv =(TextView) getActivity().findViewById(R.id.wait_receive_tv);

        JDApplication applicationInfo = (JDApplication) getActivity().getApplication();
        RLoginResult mRLoginResult = applicationInfo.mRLoginResult;
        mUserNameTv.setText(mRLoginResult.getUserName());
        initUserLevel(mRLoginResult);
        mWaitPayTv.setText(mRLoginResult.getWaitPayCount()+"");
        mWaitReciveTv.setText(mRLoginResult.getWaitReceiveCount()+"");
    }


}
