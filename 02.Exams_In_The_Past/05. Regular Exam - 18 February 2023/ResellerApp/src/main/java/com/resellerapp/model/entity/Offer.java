package com.resellerapp.model.entity;

import com.resellerapp.model.binding.OfferAddBindingModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String description;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "condition_id", referencedColumnName = "id")
    private Condition condition;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "bought_by", referencedColumnName = "id")
    private User boughtBy;

    public Offer(OfferAddBindingModel offerAddBindingModel, Condition condition, User user) {
        this.description = offerAddBindingModel.getDescription();
        this.price = offerAddBindingModel.getPrice();
        this.condition = condition;
        this.createdBy = user;
    }
}
