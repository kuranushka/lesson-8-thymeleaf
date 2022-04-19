package ru.kuranov.lesson8thymeleaf.model.dto.product;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull
    @Min(value = 1)
    private Long quantity;
}
