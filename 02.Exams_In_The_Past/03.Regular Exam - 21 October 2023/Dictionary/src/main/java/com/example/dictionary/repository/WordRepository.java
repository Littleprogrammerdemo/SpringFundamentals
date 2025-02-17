package com.example.dictionary.repository;

import com.example.dictionary.model.entity.LanguageName;
import com.example.dictionary.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, String> {

    List<Word> findWordByLanguageName(LanguageName language);
}
