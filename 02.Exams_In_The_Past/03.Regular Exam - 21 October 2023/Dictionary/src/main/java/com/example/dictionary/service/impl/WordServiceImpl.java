package com.example.dictionary.service.impl;

import com.example.dictionary.model.entity.Language;
import com.example.dictionary.model.entity.LanguageName;
import com.example.dictionary.model.entity.Word;
import com.example.dictionary.model.service.WordServiceModel;
import com.example.dictionary.repository.WordRepository;
import com.example.dictionary.service.LanguageService;
import com.example.dictionary.service.UserService;
import com.example.dictionary.service.WordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final LanguageService languageService;
    private final UserService userService;

    @Override
    public boolean add(WordServiceModel wordServiceModel, String username) {
        Language language = this.languageService.findByName(wordServiceModel.getLanguage());

        if (language != null) {
            Word word = this.modelMapper.map(wordServiceModel, Word.class);
//            Word word = new Word();
//            word.setTerm(wordServiceModel.getTerm());
//            word.setTranslation(wordServiceModel.getTranslation());
//            word.setExample(wordServiceModel.getExample());
//            word.setInputDate(wordServiceModel.getInputDate());
            word.setLanguage(language);
            word.setAddedBy(userService.findUserByUsername(username));
            this.wordRepository.save(word);
            log.info("Successfully added word.");
            return true;
        } else {
            log.info("Failed to add word.");
            return false;
        }

    }

    @Override
    public List<WordServiceModel> getWordsByLanguage(LanguageName language) {

        return wordRepository.findWordByLanguageName(language)
                .stream()
                .map(word -> {
                    WordServiceModel wordServiceModel = this.modelMapper.map(word, WordServiceModel.class);
                    wordServiceModel.setInputDate(word.getInputDate());
                    wordServiceModel.setLanguage(word.getLanguage().getName());
                    wordServiceModel.setAddedBy(word.getAddedBy());
                    return wordServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public long getTotalWordCount() {
        return this.wordRepository.count();
    }

    @Override
    public void delete(String id) {
        this.wordRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.wordRepository.deleteAll();
    }

}
