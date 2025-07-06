package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.User;

public interface UserDao {

    int getUserIdByUsername(String username);

    User getUserByUsername(String username);
}
