package com.bonappetit.model.entity;

import com.bonappetit.model.entity.enums.CategoryNameEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Recipe> recipes;
}