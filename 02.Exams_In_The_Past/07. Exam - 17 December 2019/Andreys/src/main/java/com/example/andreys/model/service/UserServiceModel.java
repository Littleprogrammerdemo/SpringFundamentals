package com.example.andreys.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel  {

    private String id;
    private String username;
    private String password;
    private String email;
    private BigDecimal budget;
}
