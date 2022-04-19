package ru.kuranov.lesson8thymeleaf.model.service.interfaces;

import org.springframework.stereotype.Service;
import ru.kuranov.lesson8thymeleaf.model.security.AccountUser;

@Service
public interface AccountService {

    void save(AccountUser user);

    boolean findByUsername(String username);
}
