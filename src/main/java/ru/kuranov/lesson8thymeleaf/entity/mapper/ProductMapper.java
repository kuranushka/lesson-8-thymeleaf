package ru.kuranov.lesson8thymeleaf.entity.mapper;

import org.springframework.stereotype.Service;
import ru.kuranov.lesson8thymeleaf.entity.Product;
import ru.kuranov.lesson8thymeleaf.entity.dto.ProductNewDto;

@Service
public interface ProductMapper {
    Product getProduct(ProductNewDto productNewDto);
}
