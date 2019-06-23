package com.example.jdmall01.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jdmall01.R;

public class MainActivity extends BaseActivity {


    @Override
    protected void initUI() {
        //1.定义底部栏

        //2.定义顶部
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initController();
    }
}
