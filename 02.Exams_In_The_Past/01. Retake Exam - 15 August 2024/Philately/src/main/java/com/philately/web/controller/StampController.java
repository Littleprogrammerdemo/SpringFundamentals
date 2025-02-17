package com.philately.web.controller;

import com.philately.model.binding.StampAddBindingModel;
import com.philately.model.service.StampServiceModel;
import com.philately.service.StampService;
import com.philately.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/stamps")
@RequiredArgsConstructor
public class StampController {

    private final StampService stampService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/add")
    public String add(Model model) {
        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("stampAddBindingModel")) {
            model.addAttribute("stampAddBindingModel", new StampAddBindingModel());
        }

        return "add-stamp";
    }


    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("stampAddBindingModel") StampAddBindingModel stampAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("stampAddBindingModel", stampAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.stampAddBindingModel", bindingResult);
            return "redirect:add";
        }

        StampServiceModel stampServiceModel = this.modelMapper.map(stampAddBindingModel, StampServiceModel.class);

        String username = httpSession.getAttribute("username").toString();
        boolean success = this.stampService.add(stampServiceModel,  username);
        if (!success) {
            redirectAttributes.addFlashAttribute("stampAddBindingModel", stampAddBindingModel);
            return "redirect:/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/wishlist/add/{id}")
    public String addToWishlist(@PathVariable String id) {
        String username = (String) httpSession.getAttribute("username");
        userService.addToWishlist(username, id);
        return "redirect:/home";
    }

    @GetMapping("/wishlist/remove/{id}")
    public String removeFromWishlist(@PathVariable String id) {
        String username = (String) httpSession.getAttribute("username");
        userService.removeFromWishlist(username, id);
        return "redirect:/home";
    }

    @GetMapping("/wishlist/buy")
    public String buyWishlist() {
        String username = (String) httpSession.getAttribute("username");
        userService.purchaseWishlist(username);
        return "redirect:/home";
    }

}
