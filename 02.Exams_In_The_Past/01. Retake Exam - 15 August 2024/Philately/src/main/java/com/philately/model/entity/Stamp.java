package com.philately.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "stamps")
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 25)
    private String description;

    // A stamp has one paper and one paper can have many stamps.
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Paper paper;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, length = 150)
    private String imageUrl;

    // A stamp has one Owner, the User who added it.
    @ManyToOne
    private User owner;
}
