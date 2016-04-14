package com.teamlinking.single.enums;

/**
 * Created by rex on 16/4/10.
 */
public enum SexType {
    girl(Byte.parseByte("0"),"妹纸"),
    boy(Byte.parseByte("1"),"汉纸");
    private Byte key;
    private String value;

    public static SexType getEnum(Byte key){
        for (SexType it : SexType.values()) {
            if (key.equals(it.getKey())) {
                return it;
            }
        }
        return null;
    }

    SexType(Byte key, String value){
        this.key = key;
        this.value = value;
    }

    public Byte getKey() {
        return key;
    }

    public void setKey(Byte key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
