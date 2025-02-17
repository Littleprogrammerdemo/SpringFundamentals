package app.story.service;

import app.user.model.User;
import app.story.model.Story;
import app.story.model.EncounterKind;
import app.story.repository.StoryRepository;
import app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoryService {

    private final StoryRepository storyRepository;
    private final UserRepository userRepository;

    // Method to add a new story
    public Story addStory(UUID userId, String title, String description, EncounterKind kind, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Story story = Story.builder()
                .title(title)
                .description(description)
                .kind(kind)
                .date(date)
                .addedOn(LocalDate.now())
                .owner(user)
                .isVisible(false) // Initially, set to false until the user shares it
                .build();

        log.info("Adding new story for user [%s]".formatted(userId));

        return storyRepository.save(story);
    }

    // Method to update visibility of a story (share it)
    public void shareStory(UUID userId, UUID storyId) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        if (!story.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You cannot share a story you did not create.");
        }

        // Set isVisible to true when sharing
        story.setVisible(true);
        storyRepository.save(story);

        log.info("User [%s] shared story with ID [%s]".formatted(userId, storyId));
    }

    // Method to delete a story
    public void deleteStory(UUID userId, UUID storyId) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        if (!story.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You cannot delete a story you did not create.");
        }

        storyRepository.delete(story);
        log.info("User [%s] deleted story with ID [%s]".formatted(userId, storyId));
    }
    public List<Story> getAll() {

        return storyRepository.findAll();
    }
    // Method to retrieve a story by its ID
    public Story getById(UUID id) {

        return storyRepository.findById(id).orElseThrow(() -> new RuntimeException("Story with id [%s] does not exist.".formatted(id)));
    }

    }
