package com.bonappetit.model.view;

import com.bonappetit.model.entity.Recipe;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeViewModel {

    private long id;
    private String name;
    private String ingredients;

    public RecipeViewModel(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.ingredients = recipe.getIngredients();
    }
}