package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.User;

public interface UserDao {

    Integer getUserIdByUsername(String username);

    User getUserByUsername(String username);

    boolean getUserAdminStatusById(int userId);
}
