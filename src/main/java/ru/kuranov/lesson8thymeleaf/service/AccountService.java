package ru.kuranov.lesson8thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuranov.lesson8thymeleaf.entity.security.AccountUser;
import ru.kuranov.lesson8thymeleaf.repository.AccountUserRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountUserRepository userRepository;

    public void save(AccountUser user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean findByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
