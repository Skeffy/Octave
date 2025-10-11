package io.github.skeffy.octave.service;

import io.github.skeffy.octave.dao.UserDao;
import io.github.skeffy.octave.model.User;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class PrincipalService {

    private static UserDao userDao;

    public PrincipalService(UserDao userDao) {
        PrincipalService.userDao = userDao;
    }

    public static int getUserId(Principal principal) {
        return userDao.getUserIdByUsername(principal.getName());
    }

    public static User getUser(Principal principal) {
        return userDao.getUserByUsername(principal.getName());
    }

    public static boolean isUserAdmin(int userId) {
        return userDao.getUserAdminStatusById(userId);
    }
}
