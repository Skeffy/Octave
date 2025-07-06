package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Message;

public interface MessageDao {

    Message getMessages(int userId);

    Message createMessage(int senderId, int recipientId, Message message);

    int adminDeleteMessage(int userId, int messageId);

    int deleteMessageThread(int senderId, int recipientId);
}
