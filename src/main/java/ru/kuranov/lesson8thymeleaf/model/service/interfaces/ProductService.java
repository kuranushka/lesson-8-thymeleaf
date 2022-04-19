package ru.kuranov.lesson8thymeleaf.model.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.kuranov.lesson8thymeleaf.model.entity.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Long findMinCost();

    Long findMaxCost();

    Page<Product> findAllPagingAndSortingAndFiltering(Integer page, Integer productsOnPage, Long min, Long max, String sort);

    void save(Product product);

    void deleteById(Long id);
}
