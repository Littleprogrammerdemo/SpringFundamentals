package com.bonappetit.repository;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Optional<Category> findByName(CategoryNameEnum name);
}