package com.example.homeworkshop4.service;

import com.example.homeworkshop4.dto.CartCreationDto;
import com.example.homeworkshop4.dto.CartDto;
import com.example.homeworkshop4.model.Cart;

import java.util.List;

public interface CartService {

    CartDto createCart(CartCreationDto cartCreationDTO);

    List<Cart> getCarts();

    CartDto getCartById(Integer id);

    CartDto addProductToCartById(Integer id, Integer productId);

    void deleteCartById(Integer id);
}
