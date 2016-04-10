package com.teamlinking.single.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by rex on 16/4/10.
 */
public class RelationChainVO {
    private long uid;
    private List<Friend> friends = Lists.newArrayList();

    public void add(String mobilemd5, Byte register, Long uid){
        friends.add(new Friend(mobilemd5,register,uid));
    }

    class Friend {
        private String mobilemd5;
        private Byte register;
        private Long uid;

        Friend() {
        }

        Friend(String mobilemd5, Byte register, Long uid) {
            this.mobilemd5 = mobilemd5;
            this.register = register;
            this.uid = uid;
        }

        public String getMobilemd5() {
            return mobilemd5;
        }

        public void setMobilemd5(String mobilemd5) {
            this.mobilemd5 = mobilemd5;
        }

        public Byte getRegister() {
            return register;
        }

        public void setRegister(Byte register) {
            this.register = register;
        }

        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
