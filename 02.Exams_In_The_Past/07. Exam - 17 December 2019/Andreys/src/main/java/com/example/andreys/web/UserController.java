package com.example.andreys.web;

import com.example.andreys.model.binding.UserLoginBindingModel;
import com.example.andreys.model.binding.UserRegisterBindingModel;
import com.example.andreys.model.service.UserServiceModel;
import com.example.andreys.service.UserService;
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

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/register")
    public String register(Model model, HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            return "redirect:/";
        }

        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("isExists", false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            return "redirect:/";
        }

        boolean passwordMatch = userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
        if (bindingResult.hasErrors() || !passwordMatch) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
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
        if (httpSession.getAttribute("user") != null) {
            return "redirect:/";
        }

        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userLoginBindingModel, UserServiceModel.class);

        boolean isLogged = this.userService.login(userServiceModel);

        if (!isLogged) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:login";
        }

        httpSession.setAttribute("user", userServiceModel);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        httpSession.invalidate();
        return "redirect:/";
    }

}