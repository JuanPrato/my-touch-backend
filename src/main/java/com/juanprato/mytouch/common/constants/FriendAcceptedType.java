package com.juanprato.mytouch.common.constants;

public enum FriendAcceptedType {
    NOT_ACCEPTED(0),
    ACCEPTED(1);

    int value;

    FriendAcceptedType(int value) {
        this.value = value;
    }

    public int getValue() {return value;}
}
