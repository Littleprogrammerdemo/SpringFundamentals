package com.plannerapp.model.entity;

import com.plannerapp.model.entity.enums.PriorityName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PriorityName name;

    @NotNull
    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "priority", fetch = FetchType.EAGER)
    private List<Task> tasks;

    public void setName(PriorityName name) {
        this.name = name;
        setDescription(name);
    }

    private void setDescription(PriorityName name) {
        String description = "";

        switch (name) {
            case URGENT -> description = "An urgent problem that blocks the system use until the issue is resolved.";
            case IMPORTANT -> description = "A core functionality that your product is explicitly supposed to perform is compromised.";
            case LOW -> description = "Should be fixed if time permits but can be postponed.";
        }

        this.description = description;
    }
}