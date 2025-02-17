package com.example.dictionary.model.view;

import com.example.dictionary.model.entity.Language;
import com.example.dictionary.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class WordViewModel {

    private String id;
    private String term;
    private String translation;
    private String example;
    private LocalDate inputDate;
    private Language language;
    private User addedBy;
}
