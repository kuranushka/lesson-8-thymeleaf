package ru.kuranov.lesson8thymeleaf.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.kuranov.lesson8thymeleaf.entity.Cart;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.repository.CartRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@Setter
public class CartService {
    private final CartRepository cartRepository;
    private Long cartId;
    private Map<Long, Long> products;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        products = new HashMap<>();
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public void addToCart(Long productId, Long quantity) {
        if (productId <= 0) {
            return;
        }
        if (products.containsKey(productId)) {
            products.replace(productId, products.get(productId) + quantity);
        } else {
            products.put(productId, quantity);
        }
    }
}
