package com.example.homeworkshop4.controller;

import com.example.homeworkshop4.dto.ShopCreationDto;
import com.example.homeworkshop4.dto.ShopDto;
import com.example.homeworkshop4.model.Shop;
import com.example.homeworkshop4.service.ShopService;
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
@RequestMapping("api/v1/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping
    public ShopDto createShop(@Valid @RequestBody ShopCreationDto shopCreationDTO) {
        return shopService.createShop(shopCreationDTO);
    }

    @GetMapping
    public List<Shop> getShops() {
        return shopService.getShops();
    }

    @GetMapping(value = "/{id}")
    public ShopDto getShopById(@PathVariable("id") Integer id) {
        return shopService.getShopById(id);
    }

    @PatchMapping(value = "/{id}")
    public ShopDto updateShop(@PathVariable("id") Integer id,
                              @Valid @RequestParam Integer productId) {
        return shopService.addProductToShopById(id, productId);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Integer id) {
        shopService.deleteShopById(id);
    }
}
