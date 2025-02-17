package com.example.dictionary.init;

import com.example.dictionary.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitialize implements CommandLineRunner {

    private final LanguageService languageService;

    @Override
    public void run(String... args) throws Exception {

        this.languageService.initData();
    }
}
