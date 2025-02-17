package com.bonappetit.service;

import com.bonappetit.model.binding.RecipeAddBindingModel;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.enums.CategoryNameEnum;

import java.util.List;
import java.util.Map;

public interface RecipeService {

    boolean create(RecipeAddBindingModel data);

    Map<CategoryNameEnum, List<Recipe>> findAllByCategory();

    void addToFavourites(Long id, long recipeId);
}