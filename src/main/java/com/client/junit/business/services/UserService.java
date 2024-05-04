package com.client.junit.business.services;

import com.client.junit.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public User createUser(String username, String email, String password) {
        String id = UUID.randomUUID().toString();
        User user = new User(id, username, email, password);
        users.add(user);
        return user;
    }

    public void updateUser(String id, String username, String email, String password) {
        User user = findUserById(id);
        if (user != null) {
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
        }
    }

    public User findUserById(String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteUser(String id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
