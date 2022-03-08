package ru.kuranov.lesson8thymeleaf.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuranov.lesson8thymeleaf.model.service.interfaces.ProductService;
import ru.kuranov.lesson8thymeleaf.util.interfaces.Filter;
import ru.kuranov.lesson8thymeleaf.model.entity.Product;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;
    private final Filter filter;

    @GetMapping("/app/products")
    public String allProducts(Model model,
                              @RequestParam Optional<Long> min,
                              @RequestParam Optional<Long> max,
                              @RequestParam Optional<String> sort,
                              @RequestParam Optional<Integer> quantityProductsOnPage,
                              @RequestParam(name = "resetFilter", required = false, defaultValue = "false") Boolean resetFilter,
                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

        filter.resetFilter(resetFilter);

        filter.memoryProductsOnPage(quantityProductsOnPage);
        Long minFilterCost = filter.checkingMinFilterCost(min);
        Long maxFilterCost = filter.checkingMaxFilterCost(max);
        String sortDirection = filter.getSortDirection(sort);
        Integer productsOnPage = filter.getProductsOnPageMemorized();

        Page<Product> productPages = productService
                .findAllPagingAndSortingAndFiltering(page, productsOnPage, minFilterCost, maxFilterCost, sortDirection);
        int totalPages = productPages.getTotalPages();

        List<Product> productList = productPages.getContent();

        Long minCost = productService.findMinCost();
        Long maxCost = productService.findMaxCost();

        model.addAttribute("list", productList);
        model.addAttribute("page", page);
        model.addAttribute("productsOnPage", productsOnPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("minCost", minCost);
        model.addAttribute("maxCost", maxCost);
        model.addAttribute("minFilterCost", minFilterCost);
        model.addAttribute("maxFilterCost", maxFilterCost);

        return "product-view-all";
    }

    @PostMapping("/app/products")
    public String startPage() {
        return "redirect:/app/products";
    }
}
