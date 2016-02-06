package com.thaelvyn.doodle.restdoodle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Julien Frisquet
 */
public interface BeneficialOwnerRepository extends JpaRepository<BeneficialOwnerEntity, UUID> {

    List<BeneficialOwnerEntity> findAllByCompanyId(UUID companyId);
}
