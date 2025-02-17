package com.plannerapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters")
    @Column(nullable = false)
    private String description;

    @NotNull
    @Future(message = "The dueDate must be a positive in the future.")
    @Column(nullable = false)
    private LocalDate dueDate;

    @NotNull
    @ManyToOne(optional = false)
    private Priority priority;

    @ManyToOne
    private User assignee;
}
