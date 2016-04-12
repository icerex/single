package com.teamlinking.single.vo;

/**
 * Created by rex on 16/4/12.
 */
public class PersonDataVO {
    //手机号码MD5
    private String mobilemd5;
    //是否注册
    private Byte register;
    //单身朋友总数
    private Integer totalSingle;
    //总牵线次数
    private Integer totalRecommend;
    //版本号
    private Long version;

    public String getMobilemd5() {
        return mobilemd5;
    }

    public void setMobilemd5(String mobilemd5) {
        this.mobilemd5 = mobilemd5;
    }

    public Byte getRegister() {
        return register;
    }

    public void setRegister(Byte register) {
        this.register = register;
    }

    public Integer getTotalSingle() {
        return totalSingle;
    }

    public void setTotalSingle(Integer totalSingle) {
        this.totalSingle = totalSingle;
    }

    public Integer getTotalRecommend() {
        return totalRecommend;
    }

    public void setTotalRecommend(Integer totalRecommend) {
        this.totalRecommend = totalRecommend;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
