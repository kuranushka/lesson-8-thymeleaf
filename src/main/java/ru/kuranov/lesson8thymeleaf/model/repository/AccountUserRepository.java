package ru.kuranov.lesson8thymeleaf.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuranov.lesson8thymeleaf.model.security.AccountUser;

import java.util.Optional;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {
    Optional<AccountUser> findByUsername(String username);
}
