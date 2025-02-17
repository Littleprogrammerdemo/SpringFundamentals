package com.example.dictionary.service;

import com.example.dictionary.model.entity.LanguageName;
import com.example.dictionary.model.service.WordServiceModel;

import java.util.List;

public interface WordService {

    boolean add(WordServiceModel wordServiceModel, String username);

    List<WordServiceModel> getWordsByLanguage(LanguageName language);

    long getTotalWordCount();

    void delete(String id);

    void deleteAll();
}
