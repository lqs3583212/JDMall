package com.example.jdmall01.listener;

public interface IModuleChangeListener {

    //一个接口,处理返回不同UI的action
    public void  onModuleChanged(int action, Object...values);
}
