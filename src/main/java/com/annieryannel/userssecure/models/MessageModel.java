package com.annieryannel.userssecure.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModel {


    private String fromUsername;
    private String message;

    @Override
    public String toString() {
        return "MessageModel{" +
                "message='" + message + '\'' +
                ", fromUsername='" + fromUsername + '\'' +
                '}';
    }
}
