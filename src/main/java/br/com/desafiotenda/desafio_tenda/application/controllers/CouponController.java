package br.com.desafiotenda.desafio_tenda.application.controllers;

import br.com.desafiotenda.desafio_tenda.application.records.request.CreateCouponRequest;
import br.com.desafiotenda.desafio_tenda.application.records.response.CouponResponse;
import br.com.desafiotenda.desafio_tenda.application.services.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService service;

    @PostMapping
    public CouponResponse create(@RequestBody @Valid CreateCouponRequest request) {
        log.info("[start] CouponController - create");
        CouponResponse response = service.create(request);
        log.info("[finish] CouponController - create id={}", response.id());
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> findById(@PathVariable UUID id) {
        log.info("[start] CouponController - findById id={}", id);
        CouponResponse response = service.findById(id);
        log.info("[finish] CouponController - findById id={}", id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("[start] CouponController - delete id={}", id);
        service.delete(id);
        log.info("[finish] CouponController - delete id={}", id);
        return ResponseEntity.noContent().build();
    }
}
