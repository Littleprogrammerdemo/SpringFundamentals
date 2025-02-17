package com.bonappetit.web.controller;

import com.bonappetit.constants.Constants;
import com.bonappetit.model.binding.RecipeAddBindingModel;
import com.bonappetit.service.RecipeService;
import com.bonappetit.util.UserSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final UserSession userSession;

    @ModelAttribute("recipeData")
    public RecipeAddBindingModel recipeData() {
        return new RecipeAddBindingModel();
    }

    @GetMapping("/add")
    public String addRecipe() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        return "recipe-add";
    }

    @PostMapping("/add")
    public String addRecipeConfirm(@Valid RecipeAddBindingModel recipeAddBindingModel,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeData", recipeAddBindingModel);
            redirectAttributes.addFlashAttribute(Constants.BINDING_RESULT + "recipeData", bindingResult);

            return "redirect:add";
        }

        boolean success = this.recipeService.create(recipeAddBindingModel);

        if (!success) {
            redirectAttributes.addFlashAttribute("recipeData", recipeAddBindingModel);

            return "redirect:add";
        }

        return "redirect:/home";
    }

    //    @PostMapping("/add-to-favourites/{recipeId}")
    @GetMapping("/add-to-favourites/{recipeId}")
    public String addToFavourites(@PathVariable long recipeId) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        this.recipeService.addToFavourites(userSession.id(), recipeId);

        return "redirect:/home";
    }
}