package ru.kuranov.lesson8thymeleaf.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.repository.ProductRepo;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    public void save(Product product) {
        productRepo.save(product);
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    public Long findMinCost() {
        return productRepo.findMinCost();
    }

    public Long findMaxCost() {
        return productRepo.findMaxCost();
    }

    public Page<Product> findAllPagingAndSortingAndFiltering(Integer page, Long min, Long max, String direction) {
        Pageable pageable;
        if (direction.equals("asc")) {
            pageable = PageRequest.of(page, 20, Sort.by("cost").ascending());
        } else {
            pageable = PageRequest.of(page, 20, Sort.by("cost").descending());
        }
        return productRepo.findAllPagingAndSortingAndFiltering(pageable, min, max);
    }
}
