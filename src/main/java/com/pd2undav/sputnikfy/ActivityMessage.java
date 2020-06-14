package com.pd2undav.sputnikfy;

public class ActivityMessage {
    private String topic;
    private String message;

    public ActivityMessage(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }
}
