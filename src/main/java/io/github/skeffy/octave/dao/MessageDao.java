package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Message;

import java.util.List;

public interface MessageDao {

    Message getMessageById(int messageId);

    List<Message> getThreads(int userId);

    List<Message> getMessages(int rootId);

    Message createMessage(Message message);

    Message addMessage(Message message);

    int adminDeleteMessage(int userId, int messageId);

    int deleteMessageThread(int senderId, int recipientId);
}
