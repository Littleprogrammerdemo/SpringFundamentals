package com.example.andreys.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private BigDecimal price;

    @Column(nullable = false)
    @Pattern(regexp = "MALE|FEMALE$")
    private String gender;

    @ManyToOne
    private Category category;
}
