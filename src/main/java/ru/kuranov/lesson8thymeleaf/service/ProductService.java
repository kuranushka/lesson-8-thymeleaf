package ru.kuranov.lesson8thymeleaf.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Transactional(readOnly = true)
    List<Product> findAll() {
        return productRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Long findMinCost() {
        return productRepo.findMinCost();
    }

    @Transactional(readOnly = true)
    public Long findMaxCost() {
        return productRepo.findMaxCost();
    }

    @Transactional(readOnly = true)
    public Page<Product> findAllPagingAndSortingAndFiltering(Integer currentPage, Integer productsOnPage, Long min, Long max, String direction) {
        Pageable pageable;
        if (direction.equals("asc")) {
            pageable = PageRequest.of(currentPage, productsOnPage, Sort.by("cost").ascending());
        } else {
            pageable = PageRequest.of(currentPage, productsOnPage, Sort.by("cost").descending());
        }
        return productRepo.findAllPagingAndSortingAndFiltering(pageable, min, max);
    }

    public void save(Product product) {
        productRepo.save(product);
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }
}
