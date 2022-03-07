package ru.kuranov.lesson8thymeleaf.controller.util;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kuranov.lesson8thymeleaf.service.ProductService;

import java.util.Optional;

@Service
@Getter
public class FilterSolver {
    private Long minAllProductsCost;
    private Long maxAllProductsCost;
    private Long minMemoryCost;
    private Long maxMemoryCost;
    private boolean isMinCostMemorized;
    private boolean isMaxCostMemorized;
    private String sortDirection;
    private Integer productsOnPageMemorized;
    private final ProductService productService;
    private boolean isProductsOnPageMemorized;
    @Value("${products.on.page}")
    private int DEFAULT_PRODUCTS_ON_PAGE;


    public FilterSolver(ProductService productService) {
        this.productService = productService;
        sortDirection = "asc";
    }

    public Long checkingMinFilterCost(Optional<Long> optionalMin) {
        updateCosts();
        if ((optionalMin.isEmpty() || optionalMin.get() < minAllProductsCost) && !isMinCostMemorized) {
            minMemoryCost = minAllProductsCost;
            isMinCostMemorized = true;
            return minMemoryCost;
        } else if (optionalMin.isEmpty()) {
            return minMemoryCost;
        } else if (!optionalMin.get().equals(minMemoryCost)) {
            minMemoryCost = optionalMin.get();
            return minMemoryCost;
        } else {
            return minMemoryCost;
        }
    }

    public Long checkingMaxFilterCost(Optional<Long> optionalMax) {
        if ((optionalMax.isEmpty() || optionalMax.get() > maxAllProductsCost) && !isMaxCostMemorized) {
            maxMemoryCost = maxAllProductsCost;
            isMaxCostMemorized = true;
            return maxMemoryCost;
        } else if (optionalMax.isEmpty()) {
            return maxMemoryCost;
        } else if (!optionalMax.get().equals(maxMemoryCost)) {
            maxMemoryCost = optionalMax.get();
            return maxMemoryCost;
        } else {
            return maxMemoryCost;
        }
    }

    public String getSortDirection(Optional<String> optionalSort) {
        if (optionalSort.isEmpty()) {
            return sortDirection;
        } else if (!optionalSort.get().equals(sortDirection)) {
            sortDirection = optionalSort.get();
            return sortDirection;
        }
        return sortDirection;
    }

    private void updateCosts() {
        minAllProductsCost = productService.findMinCost();
        maxAllProductsCost = productService.findMaxCost();
    }

    public void resetFilter(Boolean resetFilter) {
        if (resetFilter) {
            minMemoryCost = 0L;
            maxMemoryCost = 0L;
            isMinCostMemorized = false;
            isMaxCostMemorized = false;
            isProductsOnPageMemorized = false;
            sortDirection = "asc";
        }
    }

    public void memoryProductsOnPage(Optional<Integer> productsOnPageOptional) {
        if (productsOnPageOptional.isEmpty()) {
            if (isProductsOnPageMemorized) {
                return;
            }
            productsOnPageMemorized = DEFAULT_PRODUCTS_ON_PAGE;
            isProductsOnPageMemorized = true;
        } else if (productsOnPageOptional.get().equals(productsOnPageMemorized)) {
            return;
        } else {
            productsOnPageMemorized = productsOnPageOptional.get();
            isProductsOnPageMemorized = true;
        }
    }
}
