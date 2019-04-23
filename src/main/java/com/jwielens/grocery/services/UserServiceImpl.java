package com.jwielens.grocery.services;

import com.jwielens.grocery.domain.Role;
import com.jwielens.grocery.domain.User;
import com.jwielens.grocery.repositories.RoleRepository;
import com.jwielens.grocery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Optional<Role> rol = roleRepository.findById(1L);
        Role role = rol.get();

        user.setRoles(new HashSet<Role>(Arrays.asList(role)));
        userRepository.save(user);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkDupUser(String username) {
        boolean isDup = false;

        Set<User> users = new HashSet<>();
        users.addAll(userRepository.findAll());

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)){
                isDup = true;
            }
        }
        return isDup;

    }

    @Override
    public Optional<User> findByid(Long id) {
        return userRepository.findById(id);
    }
}
