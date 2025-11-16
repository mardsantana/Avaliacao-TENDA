package br.com.desafiotenda.desafio_tenda.application.records.response;

import br.com.desafiotenda.desafio_tenda.domain.enums.CouponStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CouponResponse(
        UUID id,
        String code,
        String description,
        BigDecimal discountValue,
        LocalDateTime expirationDate,
        CouponStatus status,
        boolean published,
        boolean redeemed
) {}
