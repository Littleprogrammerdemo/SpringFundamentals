package com.example.dictionary.web.controller;

import com.example.dictionary.constant.Constants;
import com.example.dictionary.model.binding.UserLoginBindingModel;
import com.example.dictionary.model.binding.UserRegisterBindingModel;
import com.example.dictionary.model.service.UserServiceModel;
import com.example.dictionary.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/register")
    public String register(Model model, HttpSession httpSession) {

        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("isExists", false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/home";
        }

        boolean passwordMatch = userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
        if (bindingResult.hasErrors() || !passwordMatch) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(Constants.BINDING_MODEL + "userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        boolean isSaved = this.userService.register(userServiceModel);
        if (!isSaved) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("isExists", true);
            return "redirect:register";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute(Constants.BINDING_MODEL + "userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userLoginBindingModel, UserServiceModel.class);

        boolean isLogged = this.userService.login(userServiceModel);
        if (!isLogged) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {

        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        userService.logout();
        return "redirect:/";
    }
}
