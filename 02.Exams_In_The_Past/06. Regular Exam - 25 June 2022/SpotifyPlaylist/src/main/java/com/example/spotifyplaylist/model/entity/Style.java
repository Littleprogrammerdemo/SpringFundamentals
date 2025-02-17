package com.example.spotifyplaylist.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleName name;

    @Column(columnDefinition = "text", nullable = true)
    private String description;
}
