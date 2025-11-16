package br.com.desafiotenda.desafio_tenda.application.repositories;

import br.com.desafiotenda.desafio_tenda.domain.models.CouponModel;

import java.util.Optional;
import java.util.UUID;


public interface CouponRepository {
    CouponModel save(CouponModel coupon);
    Optional<CouponModel> findById(UUID id);
}
