package app.web;

import app.story.model.Story;
import app.story.service.StoryService;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.CreateStoryRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/stories")
public class StoriesController {

    private final UserService userService;
    private final StoryService storyService;

    @Autowired
    public StoriesController(UserService userService, StoryService storyService) {
        this.userService = userService;
        this.storyService = storyService;
    }

    // Add Story Page
    @GetMapping("/new")
    public ModelAndView getAddStoryPage(HttpSession session) {
        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);
        ModelAndView modelAndView = new ModelAndView("add-story");
        modelAndView.addObject("user", user);
        modelAndView.addObject("createStoryRequest", new CreateStoryRequest());
        return modelAndView;
    }

    // Create New Story
    @PostMapping
    public String createNewStory(@Valid CreateStoryRequest createStoryRequest, BindingResult bindingResult, HttpSession session) {
        UUID userId = (UUID) session.getAttribute("user_id");
        if (bindingResult.hasErrors()) {
            return "add-story"; // Return back to add-story page if there are errors
        }
        User user = userService.getById(userId);
        storyService.addStory(createStoryRequest, user);
        return "redirect:/home"; // Redirect to home after the story is created
    }

    // Home Page (with user and stories)
    @GetMapping("/home")
    public ModelAndView getHomePage(HttpSession session) {
        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);
        List<Story> userStories = storyService.getUserStories(userId);
        List<Story> allStories = storyService.getAll();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userStories", userStories);
        modelAndView.addObject("allStories", allStories);
        return modelAndView;
    }

    // View Story Details
    @GetMapping("/{storyId}")
    public ModelAndView getStoryDetails(@PathVariable UUID storyId) {
        Story story = storyService.getById(storyId);
        ModelAndView modelAndView = new ModelAndView("story");
        modelAndView.addObject("story", story);
        return modelAndView;
    }

    // Share Story (make visible)
    @PostMapping("/{storyId}/visibility")
    public String shareStory(@PathVariable UUID id) {
        Story story = storyService.getById(id);
        storyService.setVisible(story);
        return "redirect:/home";
    }

    // Remove Story
    @DeleteMapping("/{storyId}")
    public String removeStory(@PathVariable UUID storyId, UUID userId) {
        storyService.deleteStory(storyId,userId);
        return "redirect:/home";
    }
}
