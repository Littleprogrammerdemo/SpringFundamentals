package app.story.service;

import app.story.model.Story;
import app.story.repository.StoryRepository;
import app.user.model.User;
import app.web.dto.NewStoryRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    public void createNewStory(NewStoryRequest newStoryRequest, User user) {
        System.out.println();
        Story story = Story.builder()
                .title(newStoryRequest.getTitle())
                .description(newStoryRequest.getDescription())
                .kind(newStoryRequest.getKind())
                .addedBy(user)
                .build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = newStoryRequest.getDate().format(formatter);
        LocalDate date = LocalDate.parse(formattedDate, formatter);
        story.setAddedOn(date);

        story.setDate(date);

        storyRepository.save(story);
    }

    public Story findById(UUID id) {
        return storyRepository.findById(id).orElse(null);
    }

    public void changeStoryVisibility(Story story) {
        story.setVisible(true);
        storyRepository.save(story);
    }

    public void deleteStoryById(UUID id) {
            storyRepository.deleteById(id);
    }
}
