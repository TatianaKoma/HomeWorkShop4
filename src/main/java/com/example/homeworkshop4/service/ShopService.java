package com.example.homeworkshop4.service;

import com.example.homeworkshop4.dto.ShopCreationDto;
import com.example.homeworkshop4.dto.ShopDto;
import com.example.homeworkshop4.model.Shop;

import java.util.List;

public interface ShopService {

    ShopDto createShop(ShopCreationDto shopCreationDto);

    ShopDto getShopById(Integer id);

    List<Shop> getShops();

    ShopDto addProductToShopById(Integer id, Integer productId);

    void deleteShopById(Integer id);
}
