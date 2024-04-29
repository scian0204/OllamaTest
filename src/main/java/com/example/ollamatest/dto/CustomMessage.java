package com.example.ollamatest.dto;

import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CustomMessage implements Message {
    final private String content;
    final private List<Media> media;
    final private Map<String, Object> properties;
    final private MessageType messageType;

    public CustomMessage(ChatObject chatObject) {
        this.content = chatObject.getContent();
        this.media = new ArrayList<>();
        this.properties = Map.of();
        this.messageType = MessageType.fromValue(chatObject.getMessageType());
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public List<Media> getMedia() {
        return this.media;
    }

    @Override
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    @Override
    public MessageType getMessageType() {
        return this.messageType;
    }
}
