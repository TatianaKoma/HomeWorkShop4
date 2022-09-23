package com.example.homeworkshop4.service.impl;

import com.example.homeworkshop4.dto.ShopCreationDto;
import com.example.homeworkshop4.dto.ShopDto;
import com.example.homeworkshop4.exception.NotFoundException;
import com.example.homeworkshop4.mapper.ShopMapper;
import com.example.homeworkshop4.model.Product;
import com.example.homeworkshop4.model.Shop;
import com.example.homeworkshop4.repository.ProductRepository;
import com.example.homeworkshop4.repository.ShopRepository;
import com.example.homeworkshop4.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.homeworkshop4.utils.ErrorMessages.PRODUCT_NOT_EXISTS;
import static com.example.homeworkshop4.utils.ErrorMessages.SHOP_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;
    private final ShopMapper shopMapper;

    @Override
    public ShopDto createShop(ShopCreationDto shopCreationDto) {
        Shop newShop = shopMapper.toShop(shopCreationDto);
        shopRepository.save(newShop);
        return shopMapper.toShopDTO(newShop);
    }

    @Override
    public ShopDto getShopById(Integer id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(SHOP_NOT_FOUND, id)));
        return shopMapper.toShopDTO(shop);
    }

    @Override
    public List<Shop> getShops() {
        return shopRepository.findAll();
    }

    @Override
    public ShopDto addProductToShopById(Integer id, Integer productId) {
        Shop shopForUpdate = shopRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(SHOP_NOT_FOUND, id)));

        List<Product> shopProducts = shopForUpdate.getProducts();
        Product newProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_EXISTS)));
        if (Objects.equals(newProduct.getId(), shopForUpdate.getId())) {
            shopProducts.add(newProduct);
        }
        shopForUpdate.setProducts(shopProducts);
        shopRepository.save(shopForUpdate);
        return shopMapper.toShopDTO(shopForUpdate);
    }

    @Override
    public void deleteShopById(Integer id) {
        Shop shopForDelete = shopRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(SHOP_NOT_FOUND, id)));
        shopRepository.delete(shopForDelete);
    }
}
