package app.user.model;

import app.story.model.Story;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

     private String email;

     private String pictureUrl;

    @OneToMany(mappedBy = "addedBy", fetch = FetchType.EAGER)
    private List<Story> stories;


}
