package com.bonappetit.init;

import com.bonappetit.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;

    @Override
    public void run(String... args) {

        this.categoryService.initConditions();

    }
}