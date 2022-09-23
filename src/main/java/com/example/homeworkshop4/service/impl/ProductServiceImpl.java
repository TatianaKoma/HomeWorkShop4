package com.example.homeworkshop4.service.impl;

import com.example.homeworkshop4.dto.ProductCreationDto;
import com.example.homeworkshop4.dto.ProductDto;
import com.example.homeworkshop4.exception.NotFoundException;
import com.example.homeworkshop4.mapper.ProductMapper;
import com.example.homeworkshop4.model.Product;
import com.example.homeworkshop4.repository.ProductRepository;
import com.example.homeworkshop4.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.homeworkshop4.utils.ErrorMessages.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public ProductDto createProduct(ProductCreationDto productCreationDTO) {
        Product newProduct = mapper.toProduct(productCreationDTO);
        productRepository.save(newProduct);
        return mapper.toProductDTO(newProduct);
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
        return mapper.toProductDTO(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto updateProductById(Integer id, ProductCreationDto productCreationDTO) {
        Product productForUpdate = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
        productForUpdate.setName(productCreationDTO.getName());
        productForUpdate.setPrice(productCreationDTO.getPrice());
        productRepository.save(productForUpdate);
        return mapper.toProductDTO(productForUpdate);
    }

    @Override
    public void deleteProductById(Integer id) {
        Product productForDelete = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
        productRepository.delete(productForDelete);
    }
}
