package app.story.model;

import app.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EncounterKind kind;

    @NotNull
    private LocalDate date;

    @NotNull //ne e zaduljitelno
    @Column(nullable = false)
    private LocalDate addedOn = LocalDate.now();

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private boolean isVisible = false;



}
