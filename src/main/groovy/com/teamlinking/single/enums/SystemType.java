package com.teamlinking.single.enums;

/**
 * Created by admin on 16/4/8.
 */
public enum SystemType {
    ios("ios",0),
    adnroid("adnroid",1);
    private String key;
    private int value;

    public static SystemType getEnumByVaule(int value){
        for (SystemType it : SystemType.values()) {
            if (value == it.getValue()) {
                return it;
            }
        }
        return null;
    }

    public static SystemType getEnumByKey(String key){
        for (SystemType it : SystemType.values()) {
            if (key.equals(it.getKey()) ) {
                return it;
            }
        }
        return null;
    }

    SystemType(String key,int value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
