package com.example.dictionary.web.controller;

import com.example.dictionary.model.entity.LanguageName;
import com.example.dictionary.model.service.UserServiceModel;
import com.example.dictionary.model.view.WordViewModel;
import com.example.dictionary.service.WordService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class HomeController {

    private final WordService wordService;
    private final ModelMapper modelMapper;

    @GetMapping(value = {"/index", "/"})
    public ModelAndView index(HttpSession httpSession) {
        if (httpSession.getAttribute("loggedIn") != null) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession httpSession){
        if (httpSession.getAttribute("loggedIn") == null) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("home");

        List<WordViewModel> spanishWords = wordService.getWordsByLanguage(LanguageName.SPANISH)
                .stream()
                .map(wordServiceModel -> modelMapper.map(wordServiceModel, WordViewModel.class))
                .collect(Collectors.toList());

        List<WordViewModel> germanWords = wordService.getWordsByLanguage(LanguageName.GERMAN)
                .stream()
                .map(wordServiceModel -> modelMapper.map(wordServiceModel, WordViewModel.class))
                .collect(Collectors.toList());

        List<WordViewModel> frenchWords = wordService.getWordsByLanguage(LanguageName.FRENCH)
                .stream()
                .map(wordServiceModel -> modelMapper.map(wordServiceModel, WordViewModel.class))
                .collect(Collectors.toList());

        List<WordViewModel> italianWords = wordService.getWordsByLanguage(LanguageName.ITALIAN)
                .stream()
                .map(wordServiceModel -> modelMapper.map(wordServiceModel, WordViewModel.class))
                .collect(Collectors.toList());


        modelAndView.addObject("spanishWords", spanishWords);
        modelAndView.addObject("germanWords", germanWords);
        modelAndView.addObject("frenchWords", frenchWords);
        modelAndView.addObject("italianWords", italianWords);

        long totalWordCount = wordService.getTotalWordCount();
        modelAndView.addObject("allWordsCount", totalWordCount);

        String username = httpSession.getAttribute("username").toString();
        modelAndView.addObject("user", username);

        return modelAndView;
    }



}
