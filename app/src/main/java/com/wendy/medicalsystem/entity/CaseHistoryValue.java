package com.wendy.medicalsystem.entity;

import android.widget.ImageView;

import java.io.File;

import cn.bmob.v3.BmobObject;

/**
 * Created by huacao on 2018/4/11.
 */

public class CaseHistoryValue extends BmobObject {
    private User user;
    private String caseHistoryTitle;
    private File caseHistoryPicture;
    private String sicknessDescribe;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCaseHistoryTitle() {
        return caseHistoryTitle;
    }

    public void setCaseHistoryTitle(String caseHistoryTitle) {
        this.caseHistoryTitle = caseHistoryTitle;
    }

    public File getCaseHistoryPicture() {
        return caseHistoryPicture;
    }

    public void setCaseHistoryPicture(File caseHistoryPicture) {
        this.caseHistoryPicture = caseHistoryPicture;
    }

    public String getSicknessDescribe() {
        return sicknessDescribe;
    }

    public void setSicknessDescribe(String sicknessDescribe) {
        this.sicknessDescribe = sicknessDescribe;
    }
}
