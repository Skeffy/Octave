package io.github.skeffy.octave.service;

import io.github.skeffy.octave.dao.UserDao;
import io.github.skeffy.octave.model.User;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class PrincipalService {

    private UserDao userDao;

    public PrincipalService(UserDao userDao) {
        this.userDao = userDao;
    }

    int getUserId(Principal principal) {
        return userDao.getUserIdByUsername(principal.getName());
    }

    User getUser(Principal principal) {
        return userDao.getUserByUsername(principal.getName());
    }
}
