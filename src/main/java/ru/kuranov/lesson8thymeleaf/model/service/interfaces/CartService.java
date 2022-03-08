package ru.kuranov.lesson8thymeleaf.model.service.interfaces;

import org.springframework.stereotype.Service;
import ru.kuranov.lesson8thymeleaf.model.entity.Product;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface CartService {

    void saveCart();

    void addProductToCart(Long productId, Long quantity);

    Map<Product, Long> updateCart(Long productId, Long quantity);

    void deleteProductFromCart(Long productToDelete);

    Map<Product, Long> getCartProducts();

    void updateTotalCost();

     BigDecimal getTotalCartCost();
}
