package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.enums.ConditionNameEnum;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.service.ConditionService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;

    @PostConstruct
    public void initConditions() {
        if (conditionRepository.count() == 0) {
            List<Condition> conditions = new ArrayList<>();
            for (ConditionNameEnum conditionNameEnum : ConditionNameEnum.values()) {
                Condition condition = new Condition(conditionNameEnum, conditionNameEnum.getDescription());
                conditions.add(condition);
            }
            conditionRepository.saveAll(conditions);
        }
    }
}
