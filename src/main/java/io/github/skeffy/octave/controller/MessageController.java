package io.github.skeffy.octave.controller;

import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.Message;
import io.github.skeffy.octave.dao.MessageDao;
import io.github.skeffy.octave.service.PrincipalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private MessageDao messageDao;

    public MessageController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @GetMapping("/threads")
    public List<Message> getThreads(Principal principal){
        try {
            int userId = PrincipalService.getUserId(principal);
            return messageDao.getThreads(userId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @GetMapping("/messages")
    public List<Message> getMessages(Principal principal, @RequestBody int parentId) {
        try {
            int userId = PrincipalService.getUserId(principal);
            return messageDao.getMessages(parentId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PostMapping("/new")
    public Message createThread(Principal principal, @RequestBody Message message) {
        try {
            int userId = PrincipalService.getUserId(principal);
            return messageDao.createMessage(userId, message);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PostMapping("/send")
    public Message sendMessage(Principal principal, @RequestBody Message message){
        try {
            int userId = PrincipalService.getUserId(principal);
            return messageDao.addMessage(userId, message);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }
}
