package com.example.dictionary.service;

import com.example.dictionary.model.entity.Language;
import com.example.dictionary.model.entity.LanguageName;

public interface LanguageService {

    void initData();

    Language findByName(LanguageName name);

}
