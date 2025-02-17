package com.example.andreys.service.impl;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.entity.CategoryName;
import com.example.andreys.repository.CategoryRepository;
import com.example.andreys.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void initCategories() {

        if (this.categoryRepository.count() == 0) {
            List<Category> toInsert = new ArrayList<>();

            Arrays.stream(CategoryName.values())
                    .forEach(categoryEnum -> {
                        Category category = new Category();
                        category.setName(categoryEnum);
                        category.setDescription(String.format("Description for %s", categoryEnum.name()));
                        toInsert.add(category);
                    });

            this.categoryRepository.saveAll(toInsert);
        }
    }

}
