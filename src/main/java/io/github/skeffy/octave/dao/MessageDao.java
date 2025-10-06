package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Message;

import java.util.List;

public interface MessageDao {

    List<Message> getThreads(int userId);

    List<Message> getMessages(int rootId);

    Message createMessage(int senderId, int recipientId, Message message);

    int adminDeleteMessage(int userId, int messageId);

    int deleteMessageThread(int senderId, int recipientId);
}
