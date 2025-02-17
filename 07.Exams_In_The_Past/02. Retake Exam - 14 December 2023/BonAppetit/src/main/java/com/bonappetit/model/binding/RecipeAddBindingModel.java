package com.bonappetit.model.binding;

import com.bonappetit.model.entity.enums.CategoryNameEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeAddBindingModel {

    @NotNull
    @Size(min = 2, max = 40, message = "Name length must be between 2 and 40 characters!")
    private String name;

    @NotNull
    @Size(min = 2, max = 80, message = "Ingredients length must be between 2 and 80 characters!")
    private String ingredients;

    @NotNull(message = "You must select a category!")
    private CategoryNameEnum category;
}