package com.resellerapp.repository;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.enums.ConditionNameEnum;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, UUID> {
    Condition findByName(@NotNull(message = "You must select a condition!") ConditionNameEnum condition);
}
