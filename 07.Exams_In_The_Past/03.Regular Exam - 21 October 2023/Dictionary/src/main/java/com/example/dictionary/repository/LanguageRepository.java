package com.example.dictionary.repository;

import com.example.dictionary.model.entity.Language;
import com.example.dictionary.model.entity.LanguageName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {

    Optional<Language> findLanguageByName(LanguageName name);
}
