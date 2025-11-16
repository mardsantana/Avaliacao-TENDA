package br.com.desafiotenda.desafio_tenda.application.mapper;

import br.com.desafiotenda.desafio_tenda.application.records.request.CreateCouponRequest;
import br.com.desafiotenda.desafio_tenda.application.records.response.CouponResponse;
import br.com.desafiotenda.desafio_tenda.domain.exceptions.DomainException;
import br.com.desafiotenda.desafio_tenda.domain.models.CouponModel;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {

    public CouponModel toEntity(CreateCouponRequest request) {
        if (request == null) {
            throw new DomainException("request cannot be null");
        }

        CouponModel coupon = new CouponModel();

        String raw = request.code() == null ? "" : request.code();
        String sanitized = raw.replaceAll("[^A-Za-z0-9]", "");
        if (sanitized.length() < 6) {
            throw new DomainException
                    ("coupon code must have at least 6 alphanumeric characters after removing special characters");
        }
        if (sanitized.length() > 6) {
            sanitized = sanitized.substring(0, 6);
        }
        coupon.setCode(sanitized.toUpperCase());

        coupon.setDescription(request.description());
        coupon.setDiscountValue(request.discountValue());
        coupon.setExpirationDate(request.expirationDate());
        coupon.setPublished(request.published());

        return coupon;
    }

    public CouponResponse toResponse(CouponModel coupon) {
        if (coupon == null) return null;
        return new CouponResponse(
                coupon.getId(),
                coupon.getCode(),
                coupon.getDescription(),
                coupon.getDiscountValue(),
                coupon.getExpirationDate(),
                coupon.getStatus(),
                coupon.isPublished(),
                coupon.isRedeemed()
        );
    }
}
