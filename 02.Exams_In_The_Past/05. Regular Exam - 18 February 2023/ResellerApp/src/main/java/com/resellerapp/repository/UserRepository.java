package com.resellerapp.repository;

import com.resellerapp.model.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(@NotNull @Size(min = 3, max = 20) String username);

    User findByEmail(@NotNull @Email String email);
}
