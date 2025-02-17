package com.example.andreys.init;

import com.example.andreys.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitialize implements CommandLineRunner {

    private final CategoryService categoryService;

    @Override
    public void run(String... args) {

        this.categoryService.initCategories();
    }
}
