package com.teamlinking.single.vo;

import java.util.Date;

/**
 * Created by rex on 16/4/10.
 */
public class UserInfoVO {
    private Long id;
    //手机号码
    private String mobile;
    //姓名
    private String name;
    //头像
    private String avatar;
    //性别
    private Byte sex;
    //出生年月
    private Date birthday;
    //身高
    private Integer height;
    //所在地 省
    private String locationProv;
    //所在地 市
    private String locationCity;
    //故乡 省
    private String birthplaceProv;
    //故乡 市
    private String birthplaceCity;
    //学历
    private Integer degree;
    //职业
    private String job;
    //微信
    private String wechat;
    //版本号
    private Long version;
    //标签版本号
    private Long tagVersion;
    //相册版本号
    private Long albumVersion;
    //是否能联系
    private Integer isCanContact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getLocationProv() {
        return locationProv;
    }

    public void setLocationProv(String locationProv) {
        this.locationProv = locationProv;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getBirthplaceProv() {
        return birthplaceProv;
    }

    public void setBirthplaceProv(String birthplaceProv) {
        this.birthplaceProv = birthplaceProv;
    }

    public String getBirthplaceCity() {
        return birthplaceCity;
    }

    public void setBirthplaceCity(String birthplaceCity) {
        this.birthplaceCity = birthplaceCity;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getTagVersion() {
        return tagVersion;
    }

    public void setTagVersion(Long tagVersion) {
        this.tagVersion = tagVersion;
    }

    public Long getAlbumVersion() {
        return albumVersion;
    }

    public void setAlbumVersion(Long albumVersion) {
        this.albumVersion = albumVersion;
    }

    public Integer getIsCanContact() {
        return isCanContact;
    }

    public void setIsCanContact(Integer isCanContact) {
        this.isCanContact = isCanContact;
    }
}
