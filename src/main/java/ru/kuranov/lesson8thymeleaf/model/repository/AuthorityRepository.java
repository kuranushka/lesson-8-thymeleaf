package ru.kuranov.lesson8thymeleaf.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuranov.lesson8thymeleaf.model.security.AccountRole;

@Repository
public interface AuthorityRepository extends JpaRepository<AccountRole, Long> {
}
