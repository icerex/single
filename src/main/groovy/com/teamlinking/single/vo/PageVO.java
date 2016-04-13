package com.teamlinking.single.vo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by admin on 15/12/1.
 */
public class PageVO<T> {
    private List<T> result;

    private long count;

    public JSONObject toJSON(){
        return (JSONObject) JSONObject.toJSON(this);
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
