package com.teamlinking.single.vo;

import com.alibaba.fastjson.JSON;
import com.teamlinking.single.constants.BizErrorCode;

/**
 * Created by admin on 16/4/8.
 */
public class ResultVO {
    private int status;
    private int code;
    private String msg;
    private Object result;

    public static ResultVO ofSuccess(Object result){
        return new ResultVO(200,0,null,result);
    }

    public static ResultVO ofFail(BizErrorCode bizErrorCode){
        return new ResultVO(500,bizErrorCode.getCode(), bizErrorCode.getMsg(),null);
    }

    public static ResultVO ofFail(int code,String msg){
        return new ResultVO(500,code,msg,null);
    }

    ResultVO(int status,int code,String msg,Object result){
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public String toJSONString(){
        return JSON.toJSONString(this);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
