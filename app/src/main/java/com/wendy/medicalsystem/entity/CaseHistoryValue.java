package com.wendy.medicalsystem.entity;

import android.widget.ImageView;

import java.io.File;

import cn.bmob.v3.BmobObject;

/**
 * Created by huacao on 2018/4/11.
 */

public class CaseHistoryValue extends BmobObject {
    private User user;
    private String caseHistoryDate;
    private String caseHistoryTitle;
    private String caseHistoryPicture;
    private String caseHistoryContent;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCaseHistoryDate() {
        return caseHistoryDate;
    }

    public void setCaseHistoryDate(String caseHistoryDate) {
        this.caseHistoryDate = caseHistoryDate;
    }

    public String getCaseHistoryTitle() {
        return caseHistoryTitle;
    }

    public void setCaseHistoryTitle(String caseHistoryTitle) {
        this.caseHistoryTitle = caseHistoryTitle;
    }

    public String getCaseHistoryPictureUrl() {
        return caseHistoryPicture;
    }

    public void setCaseHistoryPictureUrl(String caseHistoryPicture) {
        this.caseHistoryPicture = caseHistoryPicture;
    }

    public String getCaseHistoryContent() {
        return caseHistoryContent;
    }

    public void setCaseHistoryContent(String caseHistoryContent) {
        this.caseHistoryContent = caseHistoryContent;
    }
}
