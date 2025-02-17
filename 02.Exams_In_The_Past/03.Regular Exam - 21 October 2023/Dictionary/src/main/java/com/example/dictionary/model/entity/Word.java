package com.example.dictionary.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String translation;

    private String example;

    @Column(nullable = false)
    private LocalDate inputDate;

    @ManyToOne(optional = false)
    private Language language;

    @ManyToOne
    private User addedBy;
}
