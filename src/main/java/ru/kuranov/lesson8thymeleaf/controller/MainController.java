package ru.kuranov.lesson8thymeleaf.controller;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuranov.lesson8thymeleaf.controller.util.FilterSolver;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final ProductService productService;
    private final FilterSolver filterSolver;


    public MainController(ProductService productService, FilterSolver filterSolver) {
        this.productService = productService;
        this.filterSolver = filterSolver;

    }

    @GetMapping("/app/products")
    public String allProducts(Model model,
                              @RequestParam Optional<Long> min,
                              @RequestParam Optional<Long> max,
                              @RequestParam Optional<String> sort,
                              @RequestParam Optional<Integer> quantityProductsOnPage,
                              @RequestParam(name = "resetFilter", required = false, defaultValue = "false") Boolean resetFilter,
                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

        filterSolver.resetFilter(resetFilter);

        filterSolver.memoryProductsOnPage(quantityProductsOnPage);
        Long minFilterCost = filterSolver.checkingMinFilterCost(min);
        Long maxFilterCost = filterSolver.checkingMaxFilterCost(max);
        String sortDirection = filterSolver.getSortDirection(sort);
        Integer productsOnPage = filterSolver.getProductsOnPageMemorized();

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
