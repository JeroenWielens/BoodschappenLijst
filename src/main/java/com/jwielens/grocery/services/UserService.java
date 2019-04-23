package com.jwielens.grocery.services;

import com.jwielens.grocery.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    List<User> getAllUsers();

    void deleteById(Long id);

    boolean checkDupUser(String username);

    Optional<User> findByid(Long id);
}
