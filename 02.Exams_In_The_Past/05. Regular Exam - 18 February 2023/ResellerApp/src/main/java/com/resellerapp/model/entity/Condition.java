package com.resellerapp.model.entity;

import com.resellerapp.model.entity.enums.ConditionNameEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ConditionNameEnum name;

    @NotNull
    @Column(nullable = false)
    private String description;
}
