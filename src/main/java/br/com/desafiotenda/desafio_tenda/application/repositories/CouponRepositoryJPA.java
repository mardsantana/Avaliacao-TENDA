package br.com.desafiotenda.desafio_tenda.application.repositories;

import br.com.desafiotenda.desafio_tenda.domain.models.CouponModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponRepositoryJPA extends JpaRepository<CouponModel, UUID> {}
