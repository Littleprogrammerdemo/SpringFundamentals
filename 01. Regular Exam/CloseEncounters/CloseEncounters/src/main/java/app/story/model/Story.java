package app.story.model;

 import app.user.model.User;
 import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Kind kind;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDate addedOn;

    @ManyToOne
    private User addedBy;

    private boolean isVisible = false;
}
