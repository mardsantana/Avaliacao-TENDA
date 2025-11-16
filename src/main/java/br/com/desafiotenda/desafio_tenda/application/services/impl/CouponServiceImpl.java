package br.com.desafiotenda.desafio_tenda.application.services.impl;

import br.com.desafiotenda.desafio_tenda.application.records.request.CreateCouponRequest;
import br.com.desafiotenda.desafio_tenda.application.records.response.CouponResponse;
import br.com.desafiotenda.desafio_tenda.application.mapper.CouponMapper;
import br.com.desafiotenda.desafio_tenda.application.services.CouponService;
import br.com.desafiotenda.desafio_tenda.domain.models.CouponModel;
import br.com.desafiotenda.desafio_tenda.application.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repository;
    private final CouponMapper mapper;

    @Override
    public CouponResponse create(CreateCouponRequest request) {
        log.info("[start] CouponServiceImpl - create");
        CouponModel coupon = mapper.toEntity(request);
        coupon.validateForCreation();
        repository.save(coupon);
        log.info("[finish] CouponServiceImpl - create");
        return mapper.toResponse(coupon);
    }

    @Override
    public CouponResponse findById(UUID id) {
        log.info("[start] CouponServiceImpl - findById id={}", id);
        CouponModel coupon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
        log.info("[finish] CouponServiceImpl - findById");
        return mapper.toResponse(coupon);
    }

    @Override
    public void delete(UUID id) {
        log.info("[start] CouponServiceImpl - delete id={}", id);
        CouponModel coupon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
        coupon.softDelete();
        repository.save(coupon);
        log.info("[finish] CouponServiceImpl - delete");
    }
}
