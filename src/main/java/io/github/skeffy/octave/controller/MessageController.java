package io.github.skeffy.octave.controller;

import io.github.skeffy.octave.model.Message;
import io.github.skeffy.octave.model.User;
import io.github.skeffy.octave.dao.MessageDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/message")
public class MessageController {

    private MessageDao messageDao;

    public MessageController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @GetMapping
    public Message getMessages(Principal principal){
        return null;
    }

    @PostMapping
    public Message sendMessage(User sender, User recipient){
        return null;
    }
}
