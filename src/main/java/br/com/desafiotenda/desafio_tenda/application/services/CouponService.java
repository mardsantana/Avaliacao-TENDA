package br.com.desafiotenda.desafio_tenda.application.services;

import br.com.desafiotenda.desafio_tenda.application.records.request.CreateCouponRequest;
import br.com.desafiotenda.desafio_tenda.application.records.response.CouponResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface CouponService {
    CouponResponse create(@Valid CreateCouponRequest request);
    CouponResponse findById(UUID id);
    void delete(UUID id);
}
