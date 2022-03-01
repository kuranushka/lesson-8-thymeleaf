package ru.kuranov.lesson8thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kuranov.lesson8thymeleaf.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
