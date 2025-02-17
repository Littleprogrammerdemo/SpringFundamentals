package com.example.dictionary.service;

import com.example.dictionary.model.entity.User;
import com.example.dictionary.model.service.UserServiceModel;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    boolean login(UserServiceModel userServiceModel);

    void logout();

    User findUserByUsername(String username);
}
