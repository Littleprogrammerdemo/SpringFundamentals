package com.example.andreys.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginBindingModel {

    @NotBlank(message = "")
    @Size(min = 2, message = "Username must be more than two characters!")
    private String username;

    @NotBlank(message = "")
    @Size(min = 2, message = "Password must be more than two characters!")
    private String password;
}