package com.empresa.projetoapi.service;

import com.empresa.projetoapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

        User user1 = new User(1, "Matthew", 22, "matthew@gmail.com");
        User user2 = new User(2, "Joao", 22, "Joao@gmail.com");
        User user3 = new User(3, "Maria", 22, "Maria@gmail.com");
        User user4 = new User(4, "Claudia", 22, "claudia@gmail.com");
        User user5 = new User(5, "Pedro", 22, "pedro@gmail.com");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }

    public Optional<User> getUser(Integer id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public List<User> getUsers() {
        return userList;
    }

    public User createUser(User user) {
        if (userList.stream().anyMatch(existingUser -> existingUser.getId() == user.getId())) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " already exists");
        }
        userList.add(user);
        return user;
    }

    public Optional<User> getUserById(Integer id) {
        return getUser(id);
    }

    public Optional<User> updateUserById(User user) {
        return userList.stream()
                .filter(existingUser -> existingUser.getId() == user.getId())
                .findFirst()
                .map(existingUser -> {
                    int index = userList.indexOf(existingUser);
                    userList.set(index, user);
                    return user;
                });
    }

    public Optional<String> deleteUserById(Integer id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .map(user -> {
                    userList.remove(user);
                    return "User deleted: " + user.getName();
                });
    }
}
