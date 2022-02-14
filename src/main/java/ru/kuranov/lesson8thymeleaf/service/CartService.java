package ru.kuranov.lesson8thymeleaf.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.kuranov.lesson8thymeleaf.entity.Cart;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.entity.Status;
import ru.kuranov.lesson8thymeleaf.repository.CartRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Getter
@Setter
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private Map<Long, Long> products;
    private BigDecimal totalCost;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        products = new HashMap<>();
        totalCost = BigDecimal.valueOf(0);
    }

    public void saveCart() {
        Cart cart = Cart.builder()
                .totalCost(totalCost)
                .products(getCartProducts())
                .status(Status.ACTIVE)
                .build();
        cartRepository.save(cart);
        products.clear();
        totalCost = BigDecimal.valueOf(0);
    }

    public void addProductToCart(Long productId, Long quantity) {
        if (productId <= 0) {
            return;
        }
        if (products.containsKey(productId)) {
            products.replace(productId, products.get(productId) + quantity);
        } else {
            products.put(productId, quantity);
        }
        updateTotalCost();
    }

    public Map<Product, Long> updateCart(Long productId, Long quantity) {
        for (Map.Entry<Long, Long> entry : products.entrySet()) {
            if (entry.getKey().equals(productId)) {
                entry.setValue(quantity);
            }
        }
        updateTotalCost();
        return getCartProducts();
    }

    public void deleteProductFromCart(Long productToDelete) {
        for (Map.Entry<Long, Long> entry : products.entrySet()) {
            if (entry.getKey().equals(productToDelete)) {
                products.remove(entry.getKey());
            }
        }
        updateTotalCost();
    }

    public Map<Product, Long> getCartProducts() {
        Map<Product, Long> cartProducts = new HashMap<>();
        List<Product> allProducts = productService.findAll();
        for (Product product : allProducts) {
            if (products.containsKey(product.getId())) {
                Long quantity = products.get(product.getId());
                cartProducts.put(product, quantity);
            }
        }
        return cartProducts;
    }

    public void updateTotalCost() {
        Map<Product, Long> products = getCartProducts();
        totalCost = BigDecimal.valueOf(0);
        for (Map.Entry<Product, Long> entry : products.entrySet()) {
            BigDecimal cost = entry.getKey().getCost();
            Long productQuantity = entry.getValue();
            BigDecimal summ = cost.multiply(BigDecimal.valueOf(productQuantity));
            totalCost = totalCost.add(summ);
        }
    }
}
