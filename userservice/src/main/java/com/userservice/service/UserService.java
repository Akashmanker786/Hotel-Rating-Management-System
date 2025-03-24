package com.userservice.service;

import com.userservice.entity.User;

import java.util.List;

public interface UserService {


    public List<User> getAll();

    public User addUser(User user);

    public void removeUser(String id);

    public User getById(String id);

}
