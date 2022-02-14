package ru.kuranov.lesson8thymeleaf.controller;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.entity.Status;
import ru.kuranov.lesson8thymeleaf.service.ProductService;
import ru.kuranov.lesson8thymeleaf.util.FilterSolver;

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
                              @RequestParam Optional<String> sort,
                              @RequestParam Optional<Long> min,
                              @RequestParam Optional<Long> max,
                              @RequestParam Optional<Integer> quantityProductsOnPage,
                              @RequestParam(name = "resetFilter", required = false, defaultValue = "false") Boolean resetFilter,
                              @RequestParam(name = "currentPage", required = false, defaultValue = "0") Integer currentPage){

        filterSolver.resetFilter(resetFilter);
        filterSolver.productsOnPageMemory(quantityProductsOnPage);
        Long minFilterCost = filterSolver.checkingMinFilterCost(min);
        Long maxFilterCost = filterSolver.checkingMaxFilterCost(max);
        String sortDirection = filterSolver.getSortDirection(sort);
        Integer productsOnPage = filterSolver.getProductsOnPageMemorized();
        model.addAttribute("productsOnPage", productsOnPage);

        Page<Product> productPages = productService
                .findAllPagingAndSortingAndFiltering(currentPage, productsOnPage , minFilterCost, maxFilterCost, sortDirection);
        int totalPages = productPages.getTotalPages();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);

        List<Product> productList = productPages.getContent();
        model.addAttribute("list", productList);

//        Product product = Product.builder().build();
//        model.addAttribute("product", product);

        Long minCost = productService.findMinCost();
        Long maxCost = productService.findMaxCost();
        model.addAttribute("minCost", minCost);
        model.addAttribute("maxCost", maxCost);
        model.addAttribute("minFilterCost", minFilterCost);
        model.addAttribute("maxFilterCost", maxFilterCost);

        return "viewAllProducts";
    }

    @PostMapping("/app/products")
    public String addProduct(@ModelAttribute Product product) {
        product.setStatus(Status.ACTIVE);
        productService.save(product);
        return "redirect:/app/products";
    }


}
