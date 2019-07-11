package com.example.jdmall01.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdmall01.R;
import com.example.jdmall01.constant.IdiyMessage;
import com.example.jdmall01.controller.UserController;

public class MyJDFragment extends BaseFragment implements View.OnClickListener {

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

    }


}
