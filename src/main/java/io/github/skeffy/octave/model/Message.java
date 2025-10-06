package io.github.skeffy.octave.model;

public class Message {

    private int messageId;
    private int parentId;
    private int senderId;
    private int recipientId;
    private String message;
    private Music music;

    public Message(int messageId, int senderId, int recipientId, String message, Music music) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.message = message;
        this.music = music;
    }

    public Message(int messageId, int parentId, int senderId, int recipientId, String message, Music music) {
        this.messageId = messageId;
        this.parentId = parentId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.message = message;
        this.music = music;
    }

    public Message() {
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
