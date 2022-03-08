package ru.kuranov.lesson8thymeleaf.model.mapper.impl;

import org.springframework.stereotype.Component;
import ru.kuranov.lesson8thymeleaf.model.entity.Product;
import ru.kuranov.lesson8thymeleaf.model.dto.product.ProductNewDto;
import ru.kuranov.lesson8thymeleaf.model.mapper.interfaces.ProductMapper;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product getProduct(ProductNewDto productNewDto) {
        return Product.builder()
                .id(productNewDto.getId())
                .title(productNewDto.getTitle())
                .manufacturer(productNewDto.getManufacturer())
                .description(productNewDto.getDescription())
                .cost(productNewDto.getCost())
                .status(productNewDto.getStatus())
                .build();
    }
}
