package ru.kuranov.lesson8thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.entity.dto.ProductDto;
import ru.kuranov.lesson8thymeleaf.service.CartService;
import ru.kuranov.lesson8thymeleaf.service.ProductService;

import javax.validation.Valid;
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
    public String addToCart(@Valid ProductDto productDto,
                            BindingResult bindingResult,
                            RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("errorMessage", getError());
            return "redirect:/app/products";
        }
        Optional<Product> product = productService.findById(productDto.getId());
        if (product.isPresent()) {
            cartService.addProductToCart(productDto.getId(), productDto.getQuantity());
        }
        return "redirect:/app/products";
    }

    @GetMapping("/app/products/cart")
    public String cart(Model model) {
        Map<Product, Long> products = cartService.getCartProducts();
        updateTotalCost(model);
        model.addAttribute("products", products);
        return "cart-view";
    }


    @PostMapping("/app/products/editcart")
    public String editCart(@Valid ProductDto productDto,
                           BindingResult bindingResult,
                           RedirectAttributes attributes,
                           Model model) {

        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("errorMessage", getError());
            return "redirect:/app/products/cart";
        } else {
            Map<Product, Long> products = cartService.updateCart(productDto.getId(), productDto.getQuantity());
            updateTotalCost(model);
            model.addAttribute("products", products);
            return "cart-view";
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
        return "cart-saved";
    }


    @ModelAttribute
    private void updateTotalCost(Model model) {
        BigDecimal totalCost = cartService.getTotalCartCost();
        model.addAttribute("totalCost", totalCost);
    }

    private String getError() {
        return "QUANTITY MUST BE A NUMBER AND GREATER THAN 0";
    }
}
