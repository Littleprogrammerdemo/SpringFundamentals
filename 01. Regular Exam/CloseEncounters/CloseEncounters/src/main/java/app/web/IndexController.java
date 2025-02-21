package app.web;

import app.story.model.Story;
import app.story.service.StoryService;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    private final UserService userService;
    private final StoryService storyService;

    @Autowired
    public IndexController(UserService userService, StoryService storyService) {
        this.userService = userService;
        this.storyService = storyService;
    }


    @GetMapping
    public String getIndexPage() {

        return "index";
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());

        return modelAndView;
    }

    @PostMapping("/register")
    public String processRegisterRequest(@Valid RegisterRequest registerRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerUser(registerRequest);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("loginRequest", new LoginRequest());

        return modelAndView;
    }

    @PostMapping("/login")
    public String processLoginRequest(@Valid LoginRequest loginRequest, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        User user = userService.loginUser(loginRequest);

        session.setAttribute("user_id", user.getId());

        return "redirect:/home";
    }

    @GetMapping("/home")
    public ModelAndView getHomePage(HttpSession session) {
        UUID user_id = (UUID) session.getAttribute("user_id");

        User user = userService.findById(user_id);
        List<Story> stories = storyService.getAllStories().stream()
                .filter(Story::isVisible)  // Filters only the stories that are visible
                .collect(Collectors.toList());  // Collects the result back into a list


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");

        modelAndView.addObject("user", user);
        modelAndView.addObject("stories", stories);

        return modelAndView;
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }


}


