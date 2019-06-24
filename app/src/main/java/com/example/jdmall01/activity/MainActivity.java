package com.example.jdmall01.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jdmall01.R;
import com.example.jdmall01.fragment.CategoryFragment;
import com.example.jdmall01.fragment.HomeFragment;
import com.example.jdmall01.fragment.MyJDFragment;
import com.example.jdmall01.fragment.ShopcarFragment;

public class MainActivity extends BaseActivity implements
        IBottomBarClickListener {

    private BottomBar mBottomBar;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        initUI();
    }

    @Override
    protected void initUI() {
        // 1.³õÊŒ»¯µ×²¿Àž
        mBottomBar =findViewById(R.id.bottom_bar);
        mBottomBar.setIBottomBarClickListener(this);
        // ³õÊŒ»¯¶¥²¿µÄFragment
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.top_bar, new HomeFragment());
        transaction.commit();
    }

    //	ÇÐ»»Fragment
    @Override
    public void onItemClick(int action) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (action) {
            case R.id.frag_main_ll:
                transaction.replace(R.id.top_bar, new HomeFragment());
                break;
            case R.id.frag_category_ll:
                transaction.replace(R.id.top_bar, new CategoryFragment());
                break;
            case R.id.frag_shopcar_ll:
                transaction.replace(R.id.top_bar, new ShopcarFragment());
                break;
            case R.id.frag_mine_ll:
                transaction.replace(R.id.top_bar, new MyJDFragment());
                break;
        }
        transaction.commit();
    }

}