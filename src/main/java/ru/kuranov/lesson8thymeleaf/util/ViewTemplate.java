package ru.kuranov.lesson8thymeleaf.util;


import org.springframework.stereotype.Component;
import ru.kuranov.lesson8thymeleaf.service.ProductService;

import java.util.Optional;

@Component
public class ViewTemplate {
    private Long minProductCost;
    private Long maxProductCost;
    private Long minMemory;
    private Long maxMemory;
    private String sortDirection;
    private boolean isMinMemorized;
    private boolean isMaxMemorized;
    private final ProductService productService;

    public ViewTemplate(ProductService productService) {
        this.productService = productService;
        sortDirection = "asc";
    }

    public Long checkingMinFilterCost(Optional<Long> optionalMin) {
        updateCosts();
        if ((optionalMin.isEmpty() || optionalMin.get() < minProductCost) && !isMinMemorized) {
            minMemory = minProductCost;
            isMinMemorized = true;
            return minMemory;
        } else if (optionalMin.isEmpty()) {
            return minMemory;
        } else if (optionalMin.get() != minMemory) {
            minMemory = optionalMin.get();
            return minMemory;
        } else {
            return minMemory;
        }
    }

    public Long checkingMaxFilterCost(Optional<Long> optionalMax) {
        if ((optionalMax.isEmpty() || optionalMax.get() > maxProductCost) && !isMaxMemorized) {
            maxMemory = maxProductCost;
            isMaxMemorized = true;
            return maxMemory;
        } else if (optionalMax.isEmpty()) {
            return maxMemory;
        } else if (optionalMax.get() != maxMemory) {
            maxMemory = optionalMax.get();
            return maxMemory;
        } else {
            return maxMemory;
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
        minProductCost = productService.findMinCost();
        maxProductCost = productService.findMaxCost();
    }

    public void resetFilter(Boolean resetFilter) {
        if (resetFilter) {
            minMemory = 0L;
            maxMemory = 0L;
            isMinMemorized = false;
            isMaxMemorized = false;
        }
    }
}
