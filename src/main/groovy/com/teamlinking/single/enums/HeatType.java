package com.teamlinking.single.enums;

/**
 * Created by rex on 16/4/10.
 */
public enum HeatType {
    like(Byte.parseByte("0"),">>"),
    ing(Byte.parseByte("1"),"==");
    private Byte key;
    private String value;

    public static HeatType getEnum(Byte key){
        for (HeatType it : HeatType.values()) {
            if (key.equals(it.getKey())) {
                return it;
            }
        }
        return null;
    }

    HeatType(Byte key, String value){
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
