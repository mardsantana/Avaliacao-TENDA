package br.com.desafiotenda.desafio_tenda.application.records.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public record CreateCouponRequest(

        @NotBlank
        String code,

        @NotBlank
        String description,

        @NotNull
        @DecimalMin("0.5")
        BigDecimal discountValue,

        @NotNull
        LocalDateTime expirationDate,

        boolean published
) {}
