package com.wendy.medicalsystem.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by huacao on 2018/4/23.
 */

public class article extends BmobObject {

    private User user;
    private String title;
    private String content;
    private String logo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLogo() {
        return logo;
    }
}
