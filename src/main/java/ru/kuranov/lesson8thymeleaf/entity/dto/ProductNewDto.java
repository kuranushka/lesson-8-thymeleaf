package ru.kuranov.lesson8thymeleaf.entity.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.kuranov.lesson8thymeleaf.entity.Status;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductNewDto {

    private Long id;

    @NotBlank(message = "TITLE MUST BE FILLED")
    @Size(max = 255, message = "TITLE SHOULD NOT BE LONGER THAN 255 CHARACTERS")
    private String title;

    @NotBlank(message = "MANUFACTURER MUST BE FILLED")
    @Size(max = 255, message = "MANUFACTURER SHOULD NOT BE LONGER THAN 255 CHARACTERS")
    private String manufacturer;

    @NotBlank(message = "DESCRIPTION MUST BE FILLED")
    @Size(max = 255, message = "DESCRIPTION SHOULD NOT BE LONGER THAN 255 CHARACTERS")
    private String description;

    @NotNull(message = "COST MUST BE GRATER THEN 0")
    @Min(value = 0L, message = "COST MUST BE GRATER THEN 0")
    private BigDecimal cost;

    private Status status;
}
