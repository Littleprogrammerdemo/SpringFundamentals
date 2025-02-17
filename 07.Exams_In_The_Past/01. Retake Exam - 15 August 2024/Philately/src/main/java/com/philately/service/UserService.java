package com.philately.service;

import com.philately.model.entity.User;
import com.philately.model.service.UserServiceModel;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    boolean login(UserServiceModel userServiceModel);

    void logout();

    User findUserByUsername(String username);

    void addToWishlist(String username, String stampId);

    void removeFromWishlist(String username, String stampId);

    void purchaseWishlist(String username);
}
