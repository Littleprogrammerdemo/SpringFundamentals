package com.bonappetit.service;

import com.bonappetit.model.binding.UserLoginBindingModel;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.service.UserServiceModel;

import java.util.Set;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    Set<Recipe> findFavourites(Long id);
}
