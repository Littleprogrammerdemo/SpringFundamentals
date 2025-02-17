package com.bonappetit.web.controller;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.enums.CategoryNameEnum;
import com.bonappetit.model.view.RecipeViewModel;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class HomeController {

    private final UserSession userSession;
    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping("/")
    public String nonLoggedIndex() {

        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        Map<CategoryNameEnum, List<Recipe>> allRecipes = this.recipeService.findAllByCategory();

        List<RecipeViewModel> favourites = this.userService.findFavourites(userSession.id())
                .stream()
                .map(RecipeViewModel::new)
                .toList();

        List<RecipeViewModel> cocktails = allRecipes.get(CategoryNameEnum.COCKTAIL)
                .stream()
                .map(RecipeViewModel::new)
                .toList();

        List<RecipeViewModel> mainDishes = allRecipes.get(CategoryNameEnum.MAIN_DISH)
                .stream()
                .map(RecipeViewModel::new)
                .toList();

        List<RecipeViewModel> desserts = allRecipes.get(CategoryNameEnum.DESSERT)
                .stream()
                .map(RecipeViewModel::new)
                .toList();

        model.addAttribute("cocktailsList", cocktails);
        model.addAttribute("mainDishesList", mainDishes);
        model.addAttribute("dessertsList", desserts);
        model.addAttribute("favouritesList", favourites);

        return "home";
    }
}