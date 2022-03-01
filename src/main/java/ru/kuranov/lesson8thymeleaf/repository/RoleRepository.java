package ru.kuranov.lesson8thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuranov.lesson8thymeleaf.entity.security.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
