package com.example.homeworkshop4.dto;

import com.example.homeworkshop4.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShopDto {
    private Integer id;
    private String name;
    private List<Product> products;
}
