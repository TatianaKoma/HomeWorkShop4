package com.example.homeworkshop4.service;

import com.example.homeworkshop4.dto.ProductCreationDto;
import com.example.homeworkshop4.dto.ProductDto;
import com.example.homeworkshop4.model.Product;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductCreationDto productCreationDTO);

    ProductDto getProductById(Integer id);

    List<Product> getProducts();

    ProductDto updateProductById(Integer id, ProductCreationDto productCreationDTO);

    void deleteProductById(Integer id);
}
