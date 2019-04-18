package com.jwielens.grocery.services;

import com.jwielens.grocery.domain.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
