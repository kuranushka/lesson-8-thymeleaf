package ru.kuranov.lesson8thymeleaf.util.interfaces;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface Filter {

    Long checkingMinFilterCost(Optional<Long> optionalMin);

    Long checkingMaxFilterCost(Optional<Long> optionalMax);

    String getSortDirection(Optional<String> optionalSort);

    void resetFilter(Boolean resetFilter);

    void memoryProductsOnPage(Optional<Integer> productsOnPageOptional);

     Integer getProductsOnPageMemorized();
}
