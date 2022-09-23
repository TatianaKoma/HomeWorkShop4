package com.example.homeworkshop4.controller;

import com.example.homeworkshop4.dto.CartCreationDto;
import com.example.homeworkshop4.dto.CartDto;
import com.example.homeworkshop4.model.Cart;
import com.example.homeworkshop4.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("api/v1/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public CartDto createCart(@Valid @RequestBody CartCreationDto cartCreationDTO) {
        return cartService.createCart(cartCreationDTO);
    }

    @GetMapping
    public List<Cart> getCarts() {
        return cartService.getCarts();
    }

    @GetMapping(value = "/{id}")
    public CartDto getCartById(@PathVariable("id") Integer id) {
        return cartService.getCartById(id);
    }

    @PatchMapping(value = "/{id}")
    public CartDto updateCart(@PathVariable("id") Integer id,
                              @Valid @RequestParam Integer productId) {
        return cartService.addProductToCartById(id, productId);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Integer id) {
        cartService.deleteCartById(id);
    }
}
