package com.example.ollamatest.dto;

import java.util.List;

public class ChatDto {
    List<ChatObject> chatObjectList;

    public List<ChatObject> getChatObjectList() {
        return chatObjectList;
    }

    public void setChatObjectList(List<ChatObject> chatObjectList) {
        this.chatObjectList = chatObjectList;
    }
}
