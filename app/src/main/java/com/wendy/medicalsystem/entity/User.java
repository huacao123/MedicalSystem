package com.wendy.medicalsystem.entity;


import cn.bmob.v3.BmobUser;

/**
 * Created by - on 2018/3/17.
 */

public class User extends BmobUser{

    private Boolean sex;
    private String nick;
    private Integer age;
    private String category;
    private Integer level;
    private String Community;

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCommunity() {
        return Community;
    }

    public void setCommunity(String community) {
        Community = community;
    }
}
