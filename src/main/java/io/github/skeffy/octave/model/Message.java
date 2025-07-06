package io.github.skeffy.octave.model;

public class Message {

    private int messageId;
    private User sender;
    private User recipient;
    private String message;
    private Music music;

    public Message(int messageId, User sender, User recipient, String message, Music music) {
        this.messageId = messageId;
        this.sender = sender;
        this.recipient = recipient;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
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
