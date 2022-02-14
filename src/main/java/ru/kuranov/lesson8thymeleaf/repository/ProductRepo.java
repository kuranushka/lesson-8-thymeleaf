package ru.kuranov.lesson8thymeleaf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kuranov.lesson8thymeleaf.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    @Query(value = "select min(cost) from product", nativeQuery = true)
    Long findMinCost();


    @Query(value = "select max(cost) from product", nativeQuery = true)
    Long findMaxCost();

    void deleteById(Long id);

    @Query(value="select * from Product p where p.cost between :min and :max", nativeQuery = true)
    Page<Product> findAllPagingAndSortingAndFiltering(Pageable pageable, Long min, Long max);


    List<Product> findAllByIdIn(Set<Long> ids);
}
