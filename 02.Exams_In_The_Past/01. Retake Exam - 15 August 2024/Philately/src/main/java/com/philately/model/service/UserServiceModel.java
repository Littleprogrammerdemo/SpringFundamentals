package com.philately.model.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {

    private String id;

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotBlank(message = "Email cannot be empty!")
    @Pattern(regexp = "^[^@]+@[^@]+$", message = "Email must contain @")
    private String email;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
}
