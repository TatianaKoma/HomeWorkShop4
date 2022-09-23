package com.example.homeworkshop4.mapper;

import com.example.homeworkshop4.dto.ShopCreationDto;
import com.example.homeworkshop4.dto.ShopDto;
import com.example.homeworkshop4.model.Shop;
import org.springframework.stereotype.Component;

@Component
public class ShopMapper {
    public Shop toShop(ShopCreationDto shopCreationDTO) {
        Shop shop = new Shop();
        shop.setName(shopCreationDTO.getName());
        return shop;
    }

    public ShopDto toShopDTO(Shop shop) {
        return new ShopDto(shop.getId(), shop.getName(), shop.getProducts());
    }
}
