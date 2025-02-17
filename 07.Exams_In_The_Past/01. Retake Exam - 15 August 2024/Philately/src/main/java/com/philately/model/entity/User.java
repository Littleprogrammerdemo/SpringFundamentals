package com.philately.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    // I don't set a length because encryption makes the password longer.
    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

//    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
//    private Set<Stamp> myStamps = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_wished_stamps",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "stamp_id")
    )
    private Set<Stamp> wishedStamps = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_purchased_stamps",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "stamp_id")
    )
    private Set<Stamp> purchasedStamps = new HashSet<>();

}
