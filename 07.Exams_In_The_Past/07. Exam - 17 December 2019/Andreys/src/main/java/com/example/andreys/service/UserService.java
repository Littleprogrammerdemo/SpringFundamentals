package com.example.andreys.service;

import com.example.andreys.model.service.UserServiceModel;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    boolean login(UserServiceModel userServiceModel);
}
