package com.teamlinking.single.enums;

/**
 * Created by rex on 16/4/10.
 */
public enum DegreeRange {
    highSchool(1,"高中|中专及以上"),
    undergraduate(2,"大专及以上"),
    bachelor(3,"本科及以上"),
    master(4,"硕士及以上"),
    doctor(5,"博士及以上");
    private int key;
    private String value;

    public static DegreeRange getEnum(int key){
        for (DegreeRange it : DegreeRange.values()) {
            if (key == it.getKey()) {
                return it;
            }
        }
        return null;
    }

    DegreeRange(int key, String value){
        this.key = key;
        this.value = value;
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
