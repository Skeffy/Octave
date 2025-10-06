package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.Message;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMessageDao implements MessageDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcMessageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message getMessageById(int messageId) {
        Message message = null;
        String sql = "SELECT * FROM messages WHERE message_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, messageId);
            if(results.next()) {
                message = mapRowToMessage(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        }
        return message;
    }

    @Override
    public List<Message> getThreads(int userId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE parent_id = null AND (recipient_id = ? OR sender_id = ?) ORDER BY time;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
            while(results.next()) {
                Message message = mapRowToMessage(results);
                messages.add(message);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return messages;
    }

    @Override
    public List<Message> getMessages(int rootId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE parent_id = ? ORDER BY time;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, rootId);
            while(results.next()) {
                Message message = mapRowToMessage(results);
                messages.add(message);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return messages;
    }

    @Override
    public Message createMessage(Message m) {
        Message newMessage;
        String sql = "INSERT INTO messages(sender_id, recipient_id, body, link) VALUES(?, ?, ?, ?) RETURNING message_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, m.getSenderId(), m.getRecipientId(), m.getMessage(), m.getMusic());
            newMessage = getMessageById(newId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newMessage;
    }

    @Override
    public Message addMessage(Message m) {
        Message newMessage;
        String sql = "INSERT INTO messages(sender_id, recipient_id, parent_id, body, link) VALUES(?, ?, ?, ?) RETURNING message_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, m.getSenderId(), m.getRecipientId(), m.getParentId(),
                    m.getMessage(), m.getMusic());
            newMessage = getMessageById(newId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newMessage;
    }

    @Override
    public int adminDeleteMessage(int userId, int messageId) {
        return 0;
    }

    @Override
    public int deleteMessageThread(int senderId, int recipientId) {
        return 0;
    }

    private Message mapRowToMessage(SqlRowSet r) {
        Message m = new Message();
        m.setMessageId(r.getInt("message_id"));
        m.setParentId(r.getInt("parent_id"));
        m.setSenderId(r.getInt("sender_id"));
        m.setRecipientId(r.getInt("recipient_id"));
        m.setMessage(r.getString("body"));
        m.setMusic(r.getString("link"));
        return m;
    }
}
