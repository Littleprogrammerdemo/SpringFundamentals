package com.example.spotifyplaylist.model.binding;

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
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotBlank(message = "")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
}