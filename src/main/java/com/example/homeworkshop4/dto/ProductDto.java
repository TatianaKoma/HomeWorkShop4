package com.example.homeworkshop4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private BigDecimal price;
}
