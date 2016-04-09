package com.teamlinking.single.enums;

/**
 * Created by rex on 16/4/10.
 */
public enum DegreeType {
    highschool(1,"高中|中专"),
    undergraduate(2,"大专"),
    bachelor(3,"本科"),
    master(4,"硕士"),
    doctor(5,"博士");
    private int key;
    private String value;

    public static DegreeType getEnum(int key){
        for (DegreeType it : DegreeType.values()) {
            if (key == it.getKey()) {
                return it;
            }
        }
        return null;
    }

    DegreeType(int key,String value){
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
