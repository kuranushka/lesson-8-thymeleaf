package ru.kuranov.lesson8thymeleaf.model.mapper.interfaces;

import org.springframework.stereotype.Service;
import ru.kuranov.lesson8thymeleaf.model.entity.Product;
import ru.kuranov.lesson8thymeleaf.model.dto.product.ProductNewDto;

@Service
public interface ProductMapper {
    Product getProduct(ProductNewDto productNewDto);
}
