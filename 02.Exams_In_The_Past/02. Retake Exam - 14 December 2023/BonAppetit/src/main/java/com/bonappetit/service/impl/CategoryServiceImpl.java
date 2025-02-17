package com.bonappetit.service.impl;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.enums.CategoryNameEnum;
import com.bonappetit.repository.CategoryRepository;
import com.bonappetit.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void initConditions() {
        if (categoryRepository.count() == 0) {
            List<Category> categories = new ArrayList<>();
            for (CategoryNameEnum conditionNameEnum : CategoryNameEnum.values()) {
                Category category = new Category();
                category.setName(conditionNameEnum);
                category.setDescription(conditionNameEnum.getDescription());
                categories.add(category);
            }
            categoryRepository.saveAll(categories);
        }
    }
}
