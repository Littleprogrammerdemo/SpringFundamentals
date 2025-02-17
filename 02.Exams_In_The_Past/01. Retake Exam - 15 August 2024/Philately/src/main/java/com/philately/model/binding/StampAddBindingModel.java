package com.philately.model.binding;

import com.philately.model.entity.PaperName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class StampAddBindingModel {

    @NotBlank(message = "")
    @Size(min = 5, max = 20, message = "Name length must be between 5 and 20 characters!")
    private String name;

    @NotBlank(message = "")
    @Size(min = 5, max = 25, message = "Description length must be between 5 and 25 characters!")
    private String description;

    @Size(max = 150)
    @NotBlank(message = "Please enter valid image URL!")
    @URL(message = "Please enter valid image URL!")
    private String imageUrl;

    @NotNull(message = "must be greater than 0")
    @Positive(message = "must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "You must select a type of paper!")
    private PaperName paper;
}
