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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private Map<Long, Long> productsInCart;
    private BigDecimal totalCartCost;
    private Long cartId;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        productsInCart = new HashMap<>();
        totalCartCost = BigDecimal.valueOf(0);
    }

    public void saveCart() {
        Cart cart = Cart.builder()
                .id(cartId)
                .totalCost(totalCartCost)
                .products(getCartProducts())
                .status(Status.ACTIVE)
                .build();
        cartRepository.save(cart);
        productsInCart.clear();
        totalCartCost = BigDecimal.valueOf(0);
    }

    public void addProductToCart(Long productId, Long quantity) {
        if (productId <= 0) {
            return;
        }
        if (productsInCart.containsKey(productId)) {
            productsInCart.replace(productId, productsInCart.get(productId) + quantity);
        } else {
            productsInCart.put(productId, quantity);
        }
        updateTotalCost();
        Map<Product, Long> cartProducts = getCartProducts();
        if (cartId == null) {
            Cart cart = Cart.builder()
                    .products(cartProducts)
                    .status(Status.ACTIVE)
                    .totalCost(totalCartCost)
                    .build();
            cartRepository.save(cart);
        } else {
            Optional<Cart> cart = cartRepository.findById(cartId);
            if (cart.isPresent()) {
                cart.get().setProducts(cartProducts);
                cart.get().setTotalCost(totalCartCost);
                cartRepository.save(cart.get());
            }
        }
    }

    public Map<Product, Long> updateCart(Long productId, Long quantity) {
        for (Map.Entry<Long, Long> entry : productsInCart.entrySet()) {
            if (entry.getKey().equals(productId)) {
                entry.setValue(quantity);
            }
        }
        updateTotalCost();
        return getCartProducts();
    }

    public void deleteProductFromCart(Long productToDelete) {
        Map<Long, Long> filteredProduct = productsInCart.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(productToDelete))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        productsInCart = filteredProduct;
        updateTotalCost();
    }

    public Map<Product, Long> getCartProducts() {
        Map<Product, Long> cartProducts = new HashMap<>();
        List<Product> allProducts = productService.findAll();
        for (Product product : allProducts) {
            if (productsInCart.containsKey(product.getId())) {
                Long quantity = productsInCart.get(product.getId());
                cartProducts.put(product, quantity);
            }
        }
        return cartProducts;
    }

    public void updateTotalCost() {
        Map<Product, Long> products = getCartProducts();
        totalCartCost = BigDecimal.valueOf(0);
        for (Map.Entry<Product, Long> entry : products.entrySet()) {
            BigDecimal cost = entry.getKey().getCost();
            Long productQuantity = entry.getValue();
            BigDecimal fullProductCost = cost.multiply(BigDecimal.valueOf(productQuantity));
            totalCartCost = totalCartCost.add(fullProductCost);
        }
    }
}
