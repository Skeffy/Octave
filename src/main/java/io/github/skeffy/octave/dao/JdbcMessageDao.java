package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcMessageDao implements MessageDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcMessageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message getMessages(int userId) {
        return null;
    }

    @Override
    public Message createMessage(int senderId, int recipientId, Message message) {
        return null;
    }

    @Override
    public int adminDeleteMessage(int userId, int messageId) {
        return 0;
    }

    @Override
    public int deleteMessageThread(int senderId, int recipientId) {
        return 0;
    }
}
