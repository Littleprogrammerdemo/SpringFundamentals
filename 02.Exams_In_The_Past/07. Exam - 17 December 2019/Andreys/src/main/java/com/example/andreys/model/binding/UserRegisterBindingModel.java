package com.example.andreys.model.binding;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @NotBlank(message = "")
    @Size(min = 2, message = "Username must be more than two characters!")
    private String username;

    @NotBlank(message = "Email cannot be empty!")
    @Pattern(regexp = "^[^@]+@[^@]+$", message = "Email must contain @")
    private String email;

    @NotNull(message = "")
    @PositiveOrZero(message = "Budget must be more or equal to 0!")
    private BigDecimal budget;

    @NotBlank(message = "")
    @Size(min = 2, message = "Password must be more than two characters!")
    private String password;

    private String confirmPassword;
}
