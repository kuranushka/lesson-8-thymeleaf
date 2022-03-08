package ru.kuranov.lesson8thymeleaf.model.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuranov.lesson8thymeleaf.model.entity.Product;
import ru.kuranov.lesson8thymeleaf.model.repository.ProductRepository;
import ru.kuranov.lesson8thymeleaf.model.service.interfaces.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findMinCost() {
        return productRepository.findMinCost();
    }

    @Override
    @Transactional(readOnly = true)
    public Long findMaxCost() {
        return productRepository.findMaxCost();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAllPagingAndSortingAndFiltering(Integer page, Integer productsOnPage, Long min, Long max, String sort) {
        Pageable pageable;
        if (sort.equals("asc")) {
            pageable = PageRequest.of(page, productsOnPage, Sort.by("cost").ascending());
        } else {
            pageable = PageRequest.of(page, productsOnPage, Sort.by("cost").descending());
        }
        return productRepository.findAllPagingAndSortingAndFiltering(pageable, min, max);
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            productRepository.save(product);
        }
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isEmpty()) {
            productRepository.save(product);
            return;
        }
        optionalProduct.get().setTitle(product.getTitle());
        optionalProduct.get().setManufacturer(product.getManufacturer());
        optionalProduct.get().setDescription(product.getDescription());
        optionalProduct.get().setCost(product.getCost());
        optionalProduct.get().setStatus(product.getStatus());
        productRepository.save(optionalProduct.get());
    }

    @Override
    public void deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return;
        }
        productRepository.deleteById(id);
    }
}
