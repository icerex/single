package com.teamlinking.single.event;

/**
 * Created by rex on 16/4/11.
 */
public class RelationChainAddEvent {

    private long ownerId;
    private String owner;
    private String friend;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }
}
