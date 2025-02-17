package com.bonappetit.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "addedBy")
    private List<Recipe> addedRecipes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Recipe> favouriteRecipes;

    public void addFavourite(Recipe recipe) {
        this.favouriteRecipes.add(recipe);
    }
}