package br.com.desafiotenda.desafio_tenda.service_impl;

import br.com.desafiotenda.desafio_tenda.application.mapper.CouponMapper;
import br.com.desafiotenda.desafio_tenda.application.records.request.CreateCouponRequest;
import br.com.desafiotenda.desafio_tenda.application.records.response.CouponResponse;
import br.com.desafiotenda.desafio_tenda.application.repositories.CouponRepository;
import br.com.desafiotenda.desafio_tenda.application.services.impl.CouponServiceImpl;
import br.com.desafiotenda.desafio_tenda.domain.exceptions.DomainException;
import br.com.desafiotenda.desafio_tenda.domain.models.CouponModel;
import br.com.desafiotenda.desafio_tenda.domain.enums.CouponStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CouponServiceImplTest {

    @InjectMocks
    private CouponServiceImpl service;

    @Mock
    private CouponRepository repository;

    @Spy
    private CouponMapper mapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCoupon_Success() {
        CreateCouponRequest request = new CreateCouponRequest(
                "M@rd$on9898", "Cupom Teste", new BigDecimal("10"),
                LocalDateTime.now().plusDays(1), true
        );

        CouponResponse response = service.create(request);

        assertNotNull(response);
        assertEquals(6, response.code().length());
        assertEquals("MRDON9", response.code());
        assertEquals("Cupom Teste", response.description());
        assertEquals(new BigDecimal("10"), response.discountValue());
        assertTrue(response.published());
        verify(repository, times(1)).save(any(CouponModel.class));
    }

    @Test
    void createCoupon_DiscountTooLow_ThrowsException() {
        CreateCouponRequest request = new CreateCouponRequest(
                "M@rd$on9898", "Teste", new BigDecimal("0.4"), LocalDateTime.now().plusDays(1), false
        );

        DomainException ex = assertThrows(DomainException.class, () -> service.create(request));
        assertEquals("discountValue must be at least 0.5", ex.getMessage());
    }

    @Test
    void createCoupon_ExpirationInPast_ThrowsException() {
        CreateCouponRequest request = new CreateCouponRequest(
                "M@rd$on9898", "Teste", new BigDecimal("1"), LocalDateTime.now().minusDays(1), false
        );

        DomainException ex = assertThrows(DomainException.class, () -> service.create(request));
        assertEquals("expirationDate cannot be in the past", ex.getMessage());
    }

    @Test
    void createCoupon_CodeTooShort_ThrowsException() {
        CreateCouponRequest request = new CreateCouponRequest(
                "ab", "Teste", new BigDecimal("1"), LocalDateTime.now().plusDays(1), false
        );

        DomainException ex = assertThrows(DomainException.class, () -> service.create(request));
        assertTrue(ex.getMessage().contains("coupon code"));
    }

    @Test
    void createCoupon_DescriptionBlank_ThrowsException() {
        CreateCouponRequest request = new CreateCouponRequest(
                "M@rd$on9898", "   ", new BigDecimal("1"), LocalDateTime.now().plusDays(1), false
        );

        DomainException ex = assertThrows(DomainException.class, () -> service.create(request));
        assertEquals("description cannot be blank", ex.getMessage());
    }

    @Test
    void findById_Success() {
        UUID id = UUID.randomUUID();
        CouponModel model = new CouponModel();
        model.setId(id);
        model.setCode("M@rd$on9898");
        model.setDescription("Teste");
        model.setDiscountValue(new BigDecimal("10"));
        model.setExpirationDate(LocalDateTime.now().plusDays(1));
        model.setPublished(true);

        when(repository.findById(id)).thenReturn(Optional.of(model));

        CouponResponse response = service.findById(id);

        assertNotNull(response);
        assertEquals("M@rd$on9898", response.code());
        assertEquals("Teste", response.description());
    }

    @Test
    void findById_NotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.findById(id));
        assertTrue(ex.getMessage().contains("not found"));
    }

    @Test
    void deleteCoupon_Success() {
        UUID id = UUID.randomUUID();
        CouponModel model = new CouponModel();
        model.setId(id);
        model.setCode("M@rd$on9898");
        model.setDescription("Teste");
        model.setDiscountValue(new BigDecimal("10"));
        model.setExpirationDate(LocalDateTime.now().plusDays(1));
        model.setPublished(true);

        when(repository.findById(id)).thenReturn(Optional.of(model));

        service.delete(id);

        assertNotNull(model.getDeletedAt());
        assertEquals(CouponStatus.DELETED, model.getStatus());
        verify(repository, times(1)).save(model);
    }

    @Test
    void deleteCoupon_AlreadyDeleted_ThrowsException() {
        UUID id = UUID.randomUUID();
        CouponModel model = new CouponModel();
        model.setId(id);
        model.softDelete();

        when(repository.findById(id)).thenReturn(Optional.of(model));

        DomainException ex = assertThrows(DomainException.class, () -> model.softDelete());
        assertEquals("coupon already deleted", ex.getMessage());
    }
}
