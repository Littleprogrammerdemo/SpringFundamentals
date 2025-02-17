package app.story.repository;

import app.story.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StoryRepository extends JpaRepository<Story, UUID> {
    List<Story> findOwnerById(UUID userId);
    List<Story> findByVisibleTrue();
}