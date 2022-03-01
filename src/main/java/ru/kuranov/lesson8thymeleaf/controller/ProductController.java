package ru.kuranov.lesson8thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.service.ProductService;
import ru.kuranov.lesson8thymeleaf.util.FilterSolver;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final FilterSolver filterSolver;

    public ProductController(ProductService productService, FilterSolver filterSolver) {
        this.productService = productService;
        this.filterSolver = filterSolver;
    }

    @GetMapping("/app/products/{id}")
    public String oneProduct(Model model, @PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "viewProduct";
        } else {
            return "404";
        }
    }

    @GetMapping("/app/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            productService.deleteById(id);
            filterSolver.resetFilter(true);
            return "redirect:/app/products";
        } else {
            return "404";
        }
    }

    @GetMapping("/app/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "editProduct";
        } else {
            return "404";
        }
    }

    @PostMapping("/app/products/edit/{id}")
    public String edit(Product product) {
        productService.save(product);
        filterSolver.resetFilter(true);
        return "redirect:/app/products";
    }

    @GetMapping("/app/products/add")
    public String addProduct(Model model) {
        Product product = Product.builder().build();
        model.addAttribute("product", product);
        return "addProduct";
    }

    @PostMapping("/app/products/add")
    public String addProduct(Product product) {
        productService.save(product);
        filterSolver.resetFilter(true);
        return "redirect:/app/products";
    }

}
