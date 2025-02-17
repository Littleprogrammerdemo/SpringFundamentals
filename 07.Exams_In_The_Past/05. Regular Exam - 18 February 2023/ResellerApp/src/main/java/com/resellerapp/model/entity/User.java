package com.resellerapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    @Size(min = 3, max = 20)
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "createdBy")
    private Set<Offer> offers;

    @OneToMany(mappedBy = "boughtBy")
    private Set<Offer> boughtOffers;
}
