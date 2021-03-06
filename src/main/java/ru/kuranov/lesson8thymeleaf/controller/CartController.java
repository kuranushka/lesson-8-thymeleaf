package ru.kuranov.lesson8thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.service.CartService;
import ru.kuranov.lesson8thymeleaf.service.ProductService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Controller
public class CartController {

    private final ProductService productService;
    private final CartService cartService;


    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @PostMapping("/app/products/addtocart")
    public String addToCart(@RequestParam Long productId, @RequestParam(name = "quantity", required = false) Long quantity) {
        if (quantity == null || quantity == 0) {
            return "redirect:/app/products";
        }
        Optional<Product> product = productService.findById(productId);
        if (product.isPresent()) {
            cartService.addProductToCart(productId, quantity);
        }
        return "redirect:/app/products";
    }

    @GetMapping("/app/products/cart")
    public String cart(Model model) {
        Map<Product, Long> products = cartService.getCartProducts();
        updateTotalCost(model);
        model.addAttribute("products", products);
        return "cart";
    }


    @PostMapping("/app/products/editcart")
    public String editCart(@RequestParam(name = "quantity", required = false) Long quantity,
                           @RequestParam(name = "productId", required = false) Long productId,
                           Model model) {
        if (quantity > 0 && productId >= 0) {
            Map<Product, Long> products = cartService.updateCart(productId, quantity);
            updateTotalCost(model);
            model.addAttribute("products", products);
            return "cart";
        } else {
            return "redirect:/app/products/cart";
        }
    }

    @PostMapping("/app/products/deletefromcart/{productToDelete}")
    public String deleteProductFromCart(@PathVariable Long productToDelete) {
        cartService.deleteProductFromCart(productToDelete);
        return "redirect:/app/products/cart";
    }

    @GetMapping("/app/products/savecart")
    public String saveCart(Model model) {
        if (cartService.getCartProducts().isEmpty()) {
            model.addAttribute("message", "CART IS EMPTY... NOTHING TO SAVE");
        } else {
            cartService.saveCart();
            model.addAttribute("message", "THANK YOU!");
        }
        return "cartWasSaved";
    }


    @ModelAttribute
    private void updateTotalCost(Model model) {
        BigDecimal totalCost = cartService.getTotalCartCost();
        model.addAttribute("totalCost", totalCost);
    }
}
