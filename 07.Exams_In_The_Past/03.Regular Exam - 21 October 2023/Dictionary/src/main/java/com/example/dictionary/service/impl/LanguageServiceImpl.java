package com.example.dictionary.service.impl;

import com.example.dictionary.constant.Constants;
import com.example.dictionary.model.entity.Language;
import com.example.dictionary.model.entity.LanguageName;
import com.example.dictionary.repository.LanguageRepository;
import com.example.dictionary.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public void initData() {

        if (this.languageRepository.count() == 0) {
            List<Language> languages = new ArrayList<>();

            for (LanguageName value : LanguageName.values()) {
                Language language = new Language();
                language.setName(value);
                language.setDescription(getInfo(value));
                languages.add(language);
            }

            languageRepository.saveAll(languages);
        }

    }

    private String getInfo(LanguageName name) {
        return switch (name) {
            case GERMAN -> Constants.GERMAN;
            case SPANISH -> Constants.SPANISH;
            case FRENCH -> Constants.FRENCH;
            case ITALIAN  -> Constants.ITALIAN ;
        };
    }

    @Override
    public Language findByName(LanguageName name) {
        return this.languageRepository.findLanguageByName(name).orElse(null);
    }
}




