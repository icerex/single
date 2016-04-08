package com.teamlinking.single.sms.mob;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 16/4/8.
 */
public class SmsVerifyKit {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(SmsVerifyKit.class);

    private String iosAppkey;
    private String andoridAppkey;
    private String address;

    /**
     * 服务端发验证服务端发送的短信
     * @return
     * @throws Exception
     */
    public ResultStatus checkcode(String phone,String code,boolean isIOS){
        MobClient client = null;
        try {
            String appkey = andoridAppkey;
            if (isIOS){
                appkey = iosAppkey;
            }
            client = new MobClient(address);
            client.addParam("appkey", appkey).addParam("phone", phone)
                    .addParam("zone", "86").addParam("code", code);
            client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            client.addRequestProperty("Accept", "application/json");
            String result = client.post();
            log.info("Mob sms verify result:"+result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("status")){
                return ResultStatus.getEnum(jsonObject.getIntValue("status"));
            }
            return ResultStatus.s468;
        } catch (Exception e){
            log.error("Mob sms verify fail",e);
            return ResultStatus.s468;
        } finally {
            client.release();
        }
    }

    public String getIosAppkey() {
        return iosAppkey;
    }

    public void setIosAppkey(String iosAppkey) {
        this.iosAppkey = iosAppkey;
    }

    public String getAndoridAppkey() {
        return andoridAppkey;
    }

    public void setAndoridAppkey(String andoridAppkey) {
        this.andoridAppkey = andoridAppkey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void main(String[] args){
        SmsVerifyKit smsVerifyKit = new SmsVerifyKit();
        smsVerifyKit.address = "https://webapi.sms.mob.com/sms/verify";
        smsVerifyKit.iosAppkey = "10fbbbbe5def5";
        smsVerifyKit.andoridAppkey = "10fbfd24fa4fb";
        System.out.println(smsVerifyKit.checkcode("18668181767","123452",false).getValue());
    }
}
