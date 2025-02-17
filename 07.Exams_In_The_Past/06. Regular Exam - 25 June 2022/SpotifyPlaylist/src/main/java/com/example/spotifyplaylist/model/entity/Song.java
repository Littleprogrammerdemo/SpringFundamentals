package com.example.spotifyplaylist.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 20)
    private String performer;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private Integer duration;

    @Column
    private LocalDate releaseDate;

    // One song has one style and one style can have many songs.
    @ManyToOne(optional = false)
    private Style style;

    // One user may have many songs and one song can be saved by many users to their playlist.
    @ManyToMany(mappedBy = "playlist")
    private Set<User> users = new HashSet<>();
}
