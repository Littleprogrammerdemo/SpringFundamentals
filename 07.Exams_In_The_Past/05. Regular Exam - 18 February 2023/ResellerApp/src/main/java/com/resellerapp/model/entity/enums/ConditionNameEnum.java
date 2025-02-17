package com.resellerapp.model.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConditionNameEnum {
    EXCELLENT("In perfect condition"),
    GOOD("Some signs of wear and tear or minor defects"),
    ACCEPTABLE("The item is fairly worn but continues to function properly");

    private final String description;
}
