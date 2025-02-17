package com.bonappetit.service.impl;

import com.bonappetit.util.UserSession;
import com.bonappetit.model.binding.RecipeAddBindingModel;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.entity.enums.CategoryNameEnum;
import com.bonappetit.repository.CategoryRepository;
import com.bonappetit.repository.RecipeRepository;
import com.bonappetit.repository.UserRepository;
import com.bonappetit.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserSession userSession;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public boolean create(RecipeAddBindingModel data) {

        if (!userSession.isLoggedIn()) {
            return false; // throw error
        }

        Optional<User> byId = userRepository.findById(userSession.id());

        if (byId.isEmpty()) {
            return false; // throw error
        }

        Optional<Category> byName = categoryRepository.findByName(data.getCategory());

        if (byName.isEmpty()) {
            return false; // throw error
        }

        Recipe recipe = new Recipe();
        recipe.setName(data.getName());
        recipe.setIngredients(data.getIngredients());
        recipe.setCategory(byName.get());
        recipe.setAddedBy(byId.get());

        this.recipeRepository.save(recipe);

        return true;
    }

    @Override
    public Map<CategoryNameEnum, List<Recipe>> findAllByCategory() {
        Map<CategoryNameEnum, List<Recipe>> result = new HashMap<>();

        List<Category> allCategories = this.categoryRepository.findAll();

        for (Category category : allCategories) {
            List<Recipe> recipes = this.recipeRepository.findAllByCategory(category);

            result.put(category.getName(), recipes);
        }

        return result;
    }

    @Override
    public void addToFavourites(Long id, long recipeId) {

        Optional<User> userOptional = this.userRepository.findById(id);
        Optional<Recipe> recipeOptional = this.recipeRepository.findById(recipeId);

        if (userOptional.isPresent() && recipeOptional.isPresent()) {
            userOptional.get().addFavourite(recipeOptional.get());
            this.userRepository.save(userOptional.get());
        }
    }

}