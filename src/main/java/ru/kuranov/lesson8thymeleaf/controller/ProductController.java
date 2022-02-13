package ru.kuranov.lesson8thymeleaf.controller;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.entity.Status;
import ru.kuranov.lesson8thymeleaf.service.ProductService;
import ru.kuranov.lesson8thymeleaf.util.ViewTemplate;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ViewTemplate viewTemplate;


    public ProductController(ProductService productService, ViewTemplate viewTemplate) {
        this.productService = productService;
        this.viewTemplate = viewTemplate;

    }

    @GetMapping("/app/products")
    public String allProducts(Model model,
                              @RequestParam Optional<String> sort,
                              @RequestParam Optional<Long> min,
                              @RequestParam Optional<Long> max,
                              @RequestParam(name = "resetFilter", required = false, defaultValue = "false") Boolean resetFilter,
                              @RequestParam(name = "currentPage", required = false, defaultValue = "0") Integer currentPage) {

        viewTemplate.resetFilter(resetFilter);

        Long minFilterCost = viewTemplate.checkingMinFilterCost(min);
        Long maxFilterCost = viewTemplate.checkingMaxFilterCost(max);
        String sortDirection = viewTemplate.getSortDirection(sort);

        Page<Product> productPages = productService
                .findAllPagingAndSortingAndFiltering(currentPage, minFilterCost, maxFilterCost, sortDirection);
        Long totalPages = productPages.getTotalElements();
        model.addAttribute("totalPages", totalPages);

        List<Product> productList = productPages.getContent();
        model.addAttribute("list", productList);

        Product product = Product.builder().build();
        model.addAttribute("product", product);

        Long minCost = productService.findMinCost();
        Long maxCost = productService.findMaxCost();
        model.addAttribute("minCost", minCost);
        model.addAttribute("maxCost", maxCost);
        model.addAttribute("minFilterCost", minFilterCost);
        model.addAttribute("maxFilterCost", maxFilterCost);


        return "all";
    }

    @PostMapping("/app/products")
    public String addProduct(@ModelAttribute Product product) {
        product.setStatus(Status.ACTIVE);
        productService.save(product);
        return "redirect:/app/products";
    }

    @GetMapping("/app/products/{id}")
    public String oneProduct(Model model, @PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product";
        } else {
            return "404";
        }
    }

    @GetMapping("/app/products/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            productService.deleteById(id);
            return "redirect:/app/products";
        } else {
            return "404";
        }
    }
}
