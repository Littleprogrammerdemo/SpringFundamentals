package com.bonappetit.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe extends BaseEntity{

    @Column(nullable = false)
    private String name;

    private String ingredients;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne
    private User addedBy;
}