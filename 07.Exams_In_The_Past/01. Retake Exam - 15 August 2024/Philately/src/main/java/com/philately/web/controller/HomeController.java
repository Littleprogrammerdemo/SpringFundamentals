package com.philately.web.controller;

import com.philately.model.view.HomeViewModel;
import com.philately.service.StampService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final StampService stampService;
    private final HttpSession httpSession;

    @GetMapping(value = {"/index", "/"})
    public String index() {
        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home(){
        if (httpSession.getAttribute("loggedIn") == null) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("home");

        String username = httpSession.getAttribute("username").toString();
        modelAndView.addObject("user", username);

        HomeViewModel homeViewModel = stampService.getHomeViewModel();
        modelAndView.addObject("homeViewModel", homeViewModel);

        return modelAndView;
    }
}
