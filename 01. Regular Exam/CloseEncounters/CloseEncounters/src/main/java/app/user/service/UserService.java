package app.user.service;

import app.user.model.User;
import app.user.repository.UserRepository;
import app.web.dto.EditProfileRequest;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(RegisterRequest registerRequest) {

        Optional<User> optionalUser = userRepository.findByUsername(registerRequest.getUsername());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("User with this username already exist.");
        }
         User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userRepository.save(user);
    }

    public User loginUser(LoginRequest loginRequest) {

        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());

        if (optionalUser.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), optionalUser.get().getPassword())) {
            throw new RuntimeException("Incorrect username or password.");
        }

        return optionalUser.get();
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id [%s] does not exist.".formatted(userId)));
    }

    public void editProfileFields(User user, EditProfileRequest editProfileRequest) {
        user.setFirstName(editProfileRequest.getFirstName());
        user.setLastName(editProfileRequest.getLastName());
        user.setEmail(editProfileRequest.getEmail());
        user.setPictureUrl(editProfileRequest.getPictureUrl());

        userRepository.save(user);
    }
}
