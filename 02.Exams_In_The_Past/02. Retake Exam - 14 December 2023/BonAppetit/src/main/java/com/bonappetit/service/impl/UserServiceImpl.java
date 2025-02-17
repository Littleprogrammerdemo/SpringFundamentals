package com.bonappetit.service.impl;

import com.bonappetit.model.binding.UserLoginBindingModel;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.service.UserServiceModel;
import com.bonappetit.repository.UserRepository;
import com.bonappetit.service.UserService;
import com.bonappetit.util.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    @Override
    public boolean register(UserServiceModel userServiceModel) {

        Optional<User> existingUser = this.userRepository
                .findByUsernameOrEmail(userServiceModel.getUsername(), userServiceModel.getEmail());

        if (existingUser.isEmpty()) {
            newUser(userServiceModel);
            return true;
        }

        return false;
    }

    private void newUser(UserServiceModel userServiceModel) {
        User user = new User();
        user.setUsername(userServiceModel.getUsername());
        user.setEmail(userServiceModel.getEmail());
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        Optional<User> byUsername = this.userRepository.findByUsername(userLoginBindingModel.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        boolean passMatch = this.passwordEncoder.matches(userLoginBindingModel.getPassword(), byUsername.get().getPassword());

        if (!passMatch) {
            return false;
        }

        this.userSession.login(byUsername.get().getId(), userLoginBindingModel.getUsername());

        return true;
    }

    @Override
    public Set<Recipe> findFavourites(Long id) {
        return this.userRepository.findById(id)
                .map(User::getFavouriteRecipes)
                .orElseGet(LinkedHashSet::new);
    }
}
