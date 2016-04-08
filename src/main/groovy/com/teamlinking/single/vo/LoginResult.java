package com.teamlinking.single.vo;

/**
 * Created by admin on 16/4/8.
 */
public class LoginResult {
    private String token;
    private String key;
    private Long uid;
    private Byte register;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Byte getRegister() {
        return register;
    }

    public void setRegister(Byte register) {
        this.register = register;
    }
}
