package ru.kuranov.lesson8thymeleaf.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kuranov.lesson8thymeleaf.model.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    @Query(value = "select min(cost) from product", nativeQuery = true)
    Long findMinCost();

    @Query(value = "select max(cost) from product", nativeQuery = true)
    Long findMaxCost();

    @Query(value = "select * from Product p where p.cost between :min and :max", nativeQuery = true)
    Page<Product> findAllPagingAndSortingAndFiltering(Pageable pageable, Long min, Long max);

    void deleteById(Long id);
}
