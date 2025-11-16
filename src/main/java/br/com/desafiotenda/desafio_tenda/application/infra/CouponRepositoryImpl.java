package br.com.desafiotenda.desafio_tenda.application.infra;

import br.com.desafiotenda.desafio_tenda.domain.models.CouponModel;
import br.com.desafiotenda.desafio_tenda.application.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CouponRepositoryImpl implements CouponRepository {

    private final CouponRepositoryJPA jpa;

    @Override
    public CouponModel save(CouponModel coupon) {
        log.info("[start] CouponRepositoryImpl - save couponId={}", coupon.getId());

        CouponModel saved = jpa.save(coupon);

        log.info("[finish] CouponRepositoryImpl - save couponId={}", saved.getId());
        return saved;
    }

    @Override
    public Optional<CouponModel> findById(UUID id) {
        log.info("[start] CouponRepositoryImpl - findById id={}", id);

        Optional<CouponModel> result = jpa.findById(id);

        log.info("[finish] CouponRepositoryImpl - findById found={}", result.isPresent());
        return result;
    }
}
