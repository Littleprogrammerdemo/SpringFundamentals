package com.example.andreys.service.impl;

import com.example.andreys.model.entity.User;
import com.example.andreys.model.service.UserServiceModel;
import com.example.andreys.repository.UserRepository;
import com.example.andreys.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean register(UserServiceModel userServiceModel) {

        Optional<User> existingUser =
                this.userRepository.findByUsernameOrEmail(userServiceModel.getUsername(), userServiceModel.getEmail());

        if (existingUser.isEmpty()) {
            User user = this.modelMapper.map(userServiceModel, User.class);
            user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
            this.userRepository.save(user);
            log.info("Successfully created new user account for username [%s] and id [%s]".formatted(user.getUsername(), user.getId()));
            return true;
        } else {
            log.info("Failed to create user account. User already exists.");
            return false;
        }
    }

    @Override
    public boolean login(UserServiceModel userServiceModel) {
        Optional<User> byUsername = this.userRepository.findByUsername(userServiceModel.getUsername());

        if (byUsername.isEmpty()) {
            log.info("User already exists.");
            return false;
        }

        boolean passMatch = this.passwordEncoder.matches(userServiceModel.getPassword(), byUsername.get().getPassword());

        if (!passMatch) {
            log.info("Password does not match.");
            return false;
        }

        log.info("Successfully logged user account with username [%s]".formatted(userServiceModel.getUsername()));
        return true;
    }
}
