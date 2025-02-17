package com.example.dictionary.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private LanguageName name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "language", fetch = FetchType.EAGER)
    private Set<Word> words = new HashSet<>();
}
