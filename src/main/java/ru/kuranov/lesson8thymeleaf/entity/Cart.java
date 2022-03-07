package ru.kuranov.lesson8thymeleaf.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "totalcost")
    private BigDecimal totalCost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cart_products")
    private Map<Product, Long> products;

    @Version
    @Column(name = "version")
    private int version;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "last_modify_by")
    private String lastModifyBy;

    @LastModifiedDate
    @Column(name = "last_modify_date")
    private LocalDateTime lastModifyDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return version == cart.version && id.equals(cart.id) && totalCost.equals(cart.totalCost) && status == cart.status && Objects.equals(products, cart.products) && Objects.equals(createdBy, cart.createdBy) && Objects.equals(createdDate, cart.createdDate) && Objects.equals(lastModifyBy, cart.lastModifyBy) && Objects.equals(lastModifyDate, cart.lastModifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCost, status, products, version, createdBy, createdDate, lastModifyBy, lastModifyDate);
    }
}
