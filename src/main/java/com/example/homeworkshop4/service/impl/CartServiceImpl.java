package com.example.homeworkshop4.service.impl;

import com.example.homeworkshop4.dto.CartCreationDto;
import com.example.homeworkshop4.dto.CartDto;
import com.example.homeworkshop4.exception.NotFoundException;
import com.example.homeworkshop4.mapper.CartMapper;
import com.example.homeworkshop4.model.Cart;
import com.example.homeworkshop4.model.Person;
import com.example.homeworkshop4.model.Product;
import com.example.homeworkshop4.repository.CartRepository;
import com.example.homeworkshop4.repository.PersonRepository;
import com.example.homeworkshop4.repository.ProductRepository;
import com.example.homeworkshop4.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.example.homeworkshop4.utils.ErrorMessages.CART_NOT_FOUND;
import static com.example.homeworkshop4.utils.ErrorMessages.PERSON_NOT_FOUND;
import static com.example.homeworkshop4.utils.ErrorMessages.PRODUCT_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final PersonRepository personRepository;
    private final CartMapper cartMapper;

    @Override
    public CartDto createCart(CartCreationDto cartCreationDTO) {
        Person person = personRepository.findById(cartCreationDTO.getPersonId())
                .orElseThrow(() -> new NotFoundException(String.format(PERSON_NOT_FOUND, cartCreationDTO.getPersonId())));
        Cart newCart = new Cart();
        if (Objects.equals(person.getId(), cartCreationDTO.getPersonId())) {
            newCart = cartMapper.toCart(cartCreationDTO);
            newCart.setSum(new BigDecimal(0));
            cartRepository.save(newCart);
        }
        return cartMapper.toCartDTO(newCart);
    }

    @Override
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @Override
    public CartDto getCartById(Integer id) {
        return cartRepository.findById(id)
                .map(cartMapper::toCartDTO)
                .orElseThrow(() -> new NotFoundException(String.format(CART_NOT_FOUND, id)));
    }

    @Override
    public CartDto addProductToCartById(Integer id, Integer productId) {
        Cart cartForUpdate = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(CART_NOT_FOUND, id)));

        List<Product> cartProducts = cartForUpdate.getProducts();
        Product newProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_EXISTS)));
        cartProducts.add(newProduct);
        BigDecimal sum = cartProducts.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        cartForUpdate.setProducts(cartProducts);
        cartForUpdate.setSum(sum);
        cartRepository.save(cartForUpdate);
        return cartMapper.toCartDTO(cartForUpdate);
    }

    @Override
    public void deleteCartById(Integer id) {
        Cart cartForDelete = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(CART_NOT_FOUND, id)));
        cartRepository.delete(cartForDelete);
    }
}
