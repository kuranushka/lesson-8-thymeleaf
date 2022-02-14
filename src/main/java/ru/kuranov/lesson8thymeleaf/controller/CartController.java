package ru.kuranov.lesson8thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.service.CartService;
import ru.kuranov.lesson8thymeleaf.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class CartController {

    private final ProductService productService;
    private final CartService cartService;


    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @PostMapping("/app/products/addtocart")
    public String addToCart(@RequestParam Long productId, @RequestParam Long quantity) {
        Optional<Product> product = productService.findById(productId);
        if (product.isPresent()) {
            cartService.addToCart(productId, quantity);
        }
        return "redirect:/app/products";
    }

    @GetMapping("/app/products/cart")
    public String cart(Model model) {
        Set<Long> ids = cartService.getProducts().keySet();
        List<Product> products = productService.findAllByIdIn(ids);
        model.addAttribute("products", products);
        return "cart";
    }
}
