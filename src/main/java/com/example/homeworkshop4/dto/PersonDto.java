package com.example.homeworkshop4.dto;

import com.example.homeworkshop4.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PersonDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private List<Cart> carts;
}
