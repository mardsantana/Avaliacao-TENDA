package br.com.desafiotenda.desafio_tenda.domain.models;


import br.com.desafiotenda.desafio_tenda.domain.enums.CouponStatus;
import br.com.desafiotenda.desafio_tenda.domain.exceptions.DomainException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CouponModel {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 6)
    private String code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal discountValue;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private boolean published;

    @Column(nullable = false)
    private boolean redeemed = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CouponStatus status = CouponStatus.ACTIVE;

    private LocalDateTime deletedAt;

    public void softDelete() {
        if (isDeleted()) {
            throw new DomainException("coupon already deleted");
        }
        this.deletedAt = LocalDateTime.now();
        this.status = CouponStatus.DELETED;
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }

    public void validateForCreation() {
        if (this.discountValue == null) {
            throw new DomainException("discountValue is required");
        }
        if (this.discountValue.compareTo(new BigDecimal("0.5")) < 0) {
            throw new DomainException("discountValue must be at least 0.5");
        }
        if (this.expirationDate == null) {
            throw new DomainException("expirationDate is required");
        }
        if (this.expirationDate.isBefore(LocalDateTime.now())) {
            throw new DomainException("expirationDate cannot be in the past");
        }
        if (this.code == null || this.code.length() != 6) {
            throw new DomainException("coupon code must have exactly 6 alphanumeric characters");
        }
        if (this.description == null || this.description.isBlank()) {
            throw new DomainException("description cannot be blank");
        }
    }

    public void markRedeemed() {
        if (this.redeemed) {
            throw new DomainException("coupon already redeemed");
        }
        this.redeemed = true;
        this.status = CouponStatus.INACTIVE;
    }
}
