package com.teamlinking.single.event;

/**
 * Created by rex on 16/4/11.
 */
public class UserRegisterEvent {
    private long uid;
    private String mobile;
    private String mobilemd5;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobilemd5() {
        return mobilemd5;
    }

    public void setMobilemd5(String mobilemd5) {
        this.mobilemd5 = mobilemd5;
    }
}
