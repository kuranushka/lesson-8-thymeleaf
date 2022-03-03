package ru.kuranov.lesson8thymeleaf.entity.security;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "account_user")
public class AccountUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

    @Builder.Default
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Builder.Default
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Builder.Default
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @Builder.Default
    @Column(name = "enabled")
    private boolean enabled = true;
}

