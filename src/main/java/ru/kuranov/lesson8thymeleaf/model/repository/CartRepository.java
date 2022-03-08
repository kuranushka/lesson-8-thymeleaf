package ru.kuranov.lesson8thymeleaf.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kuranov.lesson8thymeleaf.model.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
