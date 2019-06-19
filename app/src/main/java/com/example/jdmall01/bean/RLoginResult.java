package com.example.jdmall01.bean;

public class RLoginResult {

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public int getWaitPayCount() {
        return waitPayCount;
    }

    public int getWaitReceiveCount() {
        return waitReceiveCount;
    }

    public int getUserLevel() {
        return userLevel;
    }

    private long id;    //用户id
    private String username;    //用户名
    private String userIcon;    //头像路径
    private int waitPayCount;   //待付款数
    private int waitReceiveCount;   //待收货数
    private int userLevel;  //1注册会员  2铜牌会员   3银牌会员   4金牌会员   5钻石会员

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public void setWaitPayCount(int waitPayCount) {
        this.waitPayCount = waitPayCount;
    }

    public void setWaitReceiveCount(int waitReceiveCount) {
        this.waitReceiveCount = waitReceiveCount;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
}
