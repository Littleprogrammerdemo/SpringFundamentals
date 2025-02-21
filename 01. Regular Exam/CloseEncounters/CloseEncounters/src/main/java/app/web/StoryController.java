package app.web;

import app.story.model.Story;
import app.story.service.StoryService;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.NewStoryRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/stories")
public class StoryController {

    private final UserService userService;
    private final StoryService storyService;

    @Autowired
    public StoryController(UserService userService, StoryService storyService) {
        this.userService = userService;
        this.storyService = storyService;
    }

    @GetMapping("/new")
    public ModelAndView getAddStoryPage(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.findById(userId);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("add-story");
        mav.addObject("user", user);
        mav.addObject("newStoryRequest", new NewStoryRequest());

        return mav;
    }

    @PostMapping
    public ModelAndView addNewStory(@Valid NewStoryRequest newStoryRequest, BindingResult bindingResult, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.findById(userId);

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("add-story");
            modelAndView.addObject("user", user);
            return modelAndView;
        }


        storyService.createNewStory(newStoryRequest, user);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/{id}/visibility")
    public String changeVisibility(@PathVariable UUID id) {
        Story story = storyService.findById(id);
        storyService.changeStoryVisibility(story);
        return "redirect:/home";
    }


    @PostMapping("/{id}/delete")
    public String deleteStory(@PathVariable UUID id) {
        storyService.deleteStoryById(id);
        return "redirect:/home";
    }

    @GetMapping("/{id}/read")
    public ModelAndView readStory(@PathVariable UUID id) {
        Story story = storyService.findById(id);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("story");
        mav.addObject("story", story);

         return mav;
    }


}
