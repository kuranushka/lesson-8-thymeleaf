package ru.kuranov.lesson8thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuranov.lesson8thymeleaf.entity.security.AccountUser;
import ru.kuranov.lesson8thymeleaf.repository.AccountUserRepository;
import ru.kuranov.lesson8thymeleaf.repository.AuthorityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final AccountUserRepository accountUserRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountUser user = accountUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("NO SUCH USER"));
        return user;
    }

    @Transactional(readOnly = true)
    public List<AccountUser> findAll() {
        return accountUserRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<AccountUser> findById(Long id) {
        return accountUserRepository.findById(id);
    }

    public void save(AccountUser user) {
        accountUserRepository.save(user);
    }

    public void deleteById(Long id) {
        accountUserRepository.deleteById(id);
    }
}
