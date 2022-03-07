package ru.kuranov.lesson8thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuranov.lesson8thymeleaf.entity.security.AccountRole;
import ru.kuranov.lesson8thymeleaf.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Optional<AccountRole> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<AccountRole> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Set<AccountRole> findAllByName(String[] roles) {
        return roleRepository.findByNames(roles);
    }
}
