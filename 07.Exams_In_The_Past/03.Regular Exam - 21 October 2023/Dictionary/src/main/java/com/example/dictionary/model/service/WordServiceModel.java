package com.example.dictionary.model.service;

import com.example.dictionary.model.entity.LanguageName;
import com.example.dictionary.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class WordServiceModel {

    private String id;

    @NotBlank(message = "")
    @Size(min = 2, max = 40, message = "Term length must be between 2 and 40 characters")
    private String term;

    @NotBlank(message = "")
    @Size(min = 2, max = 80, message = "Translation length must be between 2 and 80 characters")
    private String translation;

    @Size(min = 2, max = 200, message = "Example length must be between 2 and 200 characters")
    private String example;

    @NotNull
    @PastOrPresent
    private LocalDate inputDate;

    @NotNull
    private LanguageName language;

    private User addedBy;
}
