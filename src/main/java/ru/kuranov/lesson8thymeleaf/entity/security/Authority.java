package ru.kuranov.lesson8thymeleaf.entity.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<AccountUser> accountUsers;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
