package ru.atavrel.restserver.service;

import ru.atavrel.restserver.model.User;

import java.util.List;

public interface UserService {

    User getById(Long id);

    List<User> getAll();

    User getUserByEmail(String email);

    User add(User user);

    void deleteById(Long id);

}