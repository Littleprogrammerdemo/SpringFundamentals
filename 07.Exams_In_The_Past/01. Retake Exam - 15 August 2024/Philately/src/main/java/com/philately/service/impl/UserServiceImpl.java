package com.philately.service.impl;

import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.model.service.UserServiceModel;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import com.philately.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;
    private final StampRepository stampRepository;

    @Override
    public boolean register(UserServiceModel userServiceModel) {

        Optional<User> existingUser =
                this.userRepository.findByUsernameOrEmail(userServiceModel.getUsername(), userServiceModel.getEmail());

        if (existingUser.isPresent()) {
            log.info("Failed to create user account. User already exists.");
            return false;
        } else {
            User user = this.modelMapper.map(userServiceModel, User.class);
            user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
//            User user = initializeUser(userServiceModel);
            this.userRepository.save(user);
            log.info("Successfully created new user account for username [%s] and id [%s]".formatted(user.getUsername(), user.getId()));
            return true;
        }
    }

//    private User initializeUser(UserServiceModel userServiceModel) {
//
//        return User.builder()
//                .username(userServiceModel.getUsername())
//                .password(passwordEncoder.encode(userServiceModel.getPassword()))
//                .email(userServiceModel.getEmail())
//                .build();
//    }

    @Override
    public boolean login(UserServiceModel userServiceModel) {

        Optional<User> optionalUser = this.userRepository.findByUsername(userServiceModel.getUsername());
        if (optionalUser.isEmpty()) {
            log.warn("User not exists.");
            return false;
        }

        boolean passMatch = this.passwordEncoder.matches(userServiceModel.getPassword(), optionalUser.get().getPassword());
        if (!passMatch) {
            log.warn("Password does not match.");
            return false;
        }

        httpSession.setAttribute("userId", optionalUser.get().getId());
        httpSession.setAttribute("username", optionalUser.get().getUsername());
        httpSession.setAttribute("loggedIn", true);
        log.info("Successfully logged user account with username [%s]".formatted(userServiceModel.getUsername()));
        return true;
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username) .orElse(null);
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override
    @Transactional
    public void addToWishlist(String username, String stampId) {
        User user = userRepository.findByUsername(username).orElse(null);
        Stamp stamp = stampRepository.findById(stampId).orElse(null);

        if (user != null && stamp != null && !user.getWishedStamps().contains(stamp)) {
            user.getWishedStamps().add(stamp);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void removeFromWishlist(String username, String stampId) {
        User user = userRepository.findByUsername(username).orElse(null);
        Stamp stamp = stampRepository.findById(stampId).orElse(null);

        if (user != null && stamp != null) {
            user.getWishedStamps().remove(stamp);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void purchaseWishlist(String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            user.getPurchasedStamps().addAll(user.getWishedStamps());
            user.getWishedStamps().clear();
            userRepository.save(user);
        }
    }
}
