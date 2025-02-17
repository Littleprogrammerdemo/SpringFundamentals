package com.philately.model.view;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StampViewModel {

    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private String paper;
    private String owner;
}
