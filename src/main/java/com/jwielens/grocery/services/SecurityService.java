package com.jwielens.grocery.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
