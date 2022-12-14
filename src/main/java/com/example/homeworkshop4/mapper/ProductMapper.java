package com.example.homeworkshop4.mapper;

import com.example.homeworkshop4.dto.ProductCreationDto;
import com.example.homeworkshop4.dto.ProductDto;
import com.example.homeworkshop4.exception.NotFoundException;
import com.example.homeworkshop4.model.Product;
import com.example.homeworkshop4.model.Shop;
import com.example.homeworkshop4.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.homeworkshop4.utils.ErrorMessages.SHOP_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ShopRepository shopRepository;

    public Product toProduct(ProductCreationDto productCreationDTO) {
        Product product = new Product();
        product.setName(productCreationDTO.getName());
        product.setPrice(productCreationDTO.getPrice());
        Shop shop = shopRepository.findById(productCreationDTO.getShopId())
                .orElseThrow(() -> new NotFoundException(String.format(SHOP_NOT_FOUND,
                        productCreationDTO.getShopId())));
        product.setShop(shop);
        return product;
    }

    public ProductDto toProductDTO(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }
}
