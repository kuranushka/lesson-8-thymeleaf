package ru.kuranov.lesson8thymeleaf.model.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuranov.lesson8thymeleaf.model.security.AccountUser;
import ru.kuranov.lesson8thymeleaf.model.repository.AccountUserRepository;
import ru.kuranov.lesson8thymeleaf.model.service.interfaces.AccountService;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountUserRepository userRepository;

    @Override
    public void save(AccountUser user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
