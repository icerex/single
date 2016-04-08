package com.teamlinking.single.sms.mob;

/**
 * Created by admin on 16/4/8.
 */
public enum ResultStatus {
    s200(200,"验证成功"),
    s405(405,"AppKey为空"),
    s406(406,"AppKey无效"),
    s456(456,"国家代码或手机号码为空"),
    s457(457,"手机号码格式错误"),
    s466(466,"请求校验的验证码为空"),
    s467(467,"请求校验验证码频繁（5分钟内同一个appkey的同一个号码最多只能校验三次"),
    s468(468,"验证码错误"),
    s474(474,"没有打开服务端验证开关");
    private int key;
    private String value;

    public static ResultStatus getEnum(int key){
        for (ResultStatus it : ResultStatus.values()) {
            if (key == it.getKey()) {
                return it;
            }
        }
        return s468;
    }

    ResultStatus(int key,String value){
        this.key = key;
        this.value = value;
    }

    public boolean isSuccess(){
        return s200.equals(this);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
