package com.teamlinking.single.push.apicloud;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 推送云API
 * @author wangjinzhen
 * @time 25/05/2015
 * @version 0.0.1
 */
public class Push {

    private static final String DEFAUT_DOMAIN = "https://p.apicloud.com";

    private String domain;
    private String appId;
    private String appKey;

    /**
     * @param appId
     * @param appKey
     * @param domain 为空或者null为默认https
     */
    public Push(String appId,String appKey,String domain){
        this.appId = appId;
        this.appKey = appKey;
        this.domain = domain;
    }

    private Push(){};

    /**
     * 向某个推送组所有的成员推送消息
     * @param title 消息标题
     * @param content 消息内容
     * @param type 消息类型，1:消息 2:通知
     * @param platform 0:全部平台，1：ios, 2：android
     * @param groupName 推送组名，多个组用英文逗号隔开.默认:全部组。eg.group1,group2
     * @param userIds 推送用户id, 多个用户用英文逗号分隔，eg. user1,user2
     * @return
     */
    public JSONObject pushMessage(String title,String content,int type,int platform,String groupName,String userIds){

        //设置参数
        Map<String,String> params = new HashMap<String,String>();
        params.put("title", title);
        params.put("content", content);
        params.put("type", type+"");

        params.put("platform", platform+"");
        params.put("groupName", groupName);
        params.put("userIds", userIds);
        if(null == domain || "".equals(domain)){
            domain = DEFAUT_DOMAIN;
        }
        String url = domain+"/api/push/message";
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("X-APICloud-AppId", appId);
        headers.put("X-APICloud-AppKey", ApiCloudClient.encrypt(appId,appKey,"SHA-1"));
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        return ApiCloudClient.doPost(url, headers, params, "");
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}